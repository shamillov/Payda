package com.shamilov.payda.presentation.ui.donation.active

import com.shamilov.common.base.BasePresenter
import com.shamilov.payda.R
import com.shamilov.payda.data.local.db.entity.FavoriteDonationEntity
import com.shamilov.payda.data.provider.ResourceProvider
import com.shamilov.payda.domain.interactor.DonationInteractor
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationListener
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationViewHolder
import com.shamilov.payda.presentation.ui.donation.active.viewholder.HeaderViewHolder
import com.shamilov.payda.presentation.ui.donation.completed.DonationCompletedPresenter
import com.xwray.groupie.Group
import moxy.InjectViewState
import org.koin.core.inject
import ru.yandex.money.android.sdk.*
import java.util.*

/**
 * Created by Shamilov on 20.05.2020
 */
@InjectViewState
class DonationActivePresenter : BasePresenter<DonationActiveView>(), DonationListener {

    private val TAG: String = DonationCompletedPresenter::class.java.simpleName

    private val interactor: DonationInteractor by inject()
    private val resource: ResourceProvider by inject()

    fun loadData(hasNetwork: Boolean) {
        if (hasNetwork) {
            loadDonations()
        } else {
            viewState.showNetworkError(true)
        }
    }

    //TODO: Реализовать загрузку избранных из бд
    fun refreshData() {
        launch {
            interactor.getDonations()
                .doOnSubscribe { viewState.showSwipeLoading(true) }
                .doOnSuccess {
                    viewState.showSwipeLoading(false)
                    viewState.showNetworkError(false)
                    viewState.showEmptyMessage(false)
                }
                .doOnError { viewState.showSwipeLoading(false) }
                .map { donation ->
                    val items = mutableListOf<Group>()
                    items += HeaderViewHolder()
                    items.addAll(donation.map { DonationViewHolder(it, this) })
                    return@map items
                }
                .subscribe(
                    { donation ->
                        if (donation.isNotEmpty()) {
                            viewState.onUpdate(donation)
                        } else {
                            viewState.showEmptyMessage(true)
                        }
                    },
                    { throwable -> handleError(throwable) })
        }
    }

    private fun loadDonations() {
        launch {
            interactor.getDonations()
                .doOnSubscribe { viewState.showLoading(true) }
                .subscribe(
                    { donations ->
                        val items = mutableListOf<Group>()
                        items += HeaderViewHolder()
                        interactor.getFavoritesDonation()
                            .doOnSuccess { viewState.showLoading(false) }
                            .doOnError { viewState.showLoading(false) }
                            .subscribe({ favorites ->
                                donations.forEach { donation ->
                                    donation.isFavorite = favorites.any { donation.id == it.id }
                                }

                                items.addAll(donations.map { DonationViewHolder(it, this) })

                                if (donations.isNotEmpty()) {
                                    viewState.onSuccess(items)
                                } else {
                                    viewState.showEmptyMessage(true)
                                }
                            }, { handleError(it) }
                            )
                    },
                    { throwable -> handleError(throwable) }
                )
        }
    }

    override fun onItemClick(donation: DonationEntity) {
        viewState.openDonation(donation)
    }

    override fun onDonateClick(id: Int) {
        val parameter = PaymentParameters(
            amount = Amount(100.toBigDecimal(), Currency.getInstance("RUB")),
            title = "Название товара",
            subtitle = "Описание товара",
            clientApplicationKey = resource.getString(R.string.client_application_id),
            shopId = resource.getString(R.string.shop_id),
            savePaymentMethod = SavePaymentMethod.ON,
            paymentMethodTypes = setOf(
                PaymentMethodType.BANK_CARD,
                PaymentMethodType.GOOGLE_PAY,
                PaymentMethodType.SBERBANK
            )
        )

        val testParameters = TestParameters(
            true,
            googlePayTestEnvironment = true,
            mockConfiguration = MockConfiguration(
                completeWithError = false,
                paymentAuthPassed = true,
                linkedCardsCount = 5
            )
        )

        val uiParameters = UiParameters(showLogo = false)

        viewState.donate(parameter, uiParameters)
    }

    override fun onShareClick() {
        viewState.shareDonation()
    }

    override fun onFavoriteClick(isFavorite: Boolean, id: Int) {
        if (isFavorite)
            addToFavorite(id)
        else
            deleteFromFavorite(id)

    }

    override fun onImageClick() {

    }

    private fun addToFavorite(id: Int) {
        launch {
            interactor.insertFavoriteDonation(FavoriteDonationEntity(id))
                .subscribe(
                    { viewState.showMessage(R.string.add_to_favorite) },
                    { handleError(it) }
                )
        }
    }

    private fun deleteFromFavorite(id: Int) {
        launch {
            interactor.deleteFavoriteDonation(FavoriteDonationEntity(id))
                .subscribe(
                    { viewState.showMessage(R.string.delete_from_favorite) },
                    { handleError(it) }
                )
        }
    }

}