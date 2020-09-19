package com.shamilov.payda.presentation.ui.donation.active

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.extension.hide
import com.shamilov.payda.extension.show
import com.shamilov.payda.presentation.base.BaseFragment
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator
import kotlinx.android.synthetic.main.fragment_active.*
import moxy.ktx.moxyPresenter
import ru.yandex.money.android.sdk.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActiveFragment : BaseFragment(R.layout.fragment_active), DonationActiveView {

    private val TAG = DonationActiveFragment::class.java.simpleName

    private val presenter by moxyPresenter { DonationActivePresenter() }
    private val donationAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViews()
        initListeners()
        presenter.loadData(isNetworkAvailable())
    }

    private fun initRecyclerView() {
        recyclerViewActive.apply {
            setHasFixedSize(true)
            adapter = donationAdapter
            addItemDecoration(DividerItemDecoration())
            itemAnimator = FadeInUpAnimator()
        }

        recyclerViewActive.itemAnimator?.apply {
            addDuration = 300
            removeDuration = 300
            moveDuration = 300
            changeDuration = 300
        }
    }

    private fun initViews() {
        swipeRefreshDonationActive.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )
    }

    override fun showLoading(loading: Boolean) {
        if (loading)
            progressBarActive.show()
        else
            progressBarActive.hide()
    }

    override fun showSwipeLoading(loading: Boolean) {
        swipeRefreshDonationActive.isRefreshing = loading
    }

    override fun showNetworkError(showError: Boolean) {
        if (showError) {
            tvNetworkErrorActive.show()
            recyclerViewActive.hide()
        } else {
            tvNetworkErrorActive.hide()
            recyclerViewActive.show()
        }
    }

    override fun onSuccess(data: List<Group>) {
        donationAdapter.clear()
        donationAdapter.addAll(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(data: List<Group>) {
        donationAdapter.update(data)
    }

    override fun openDonation(donation: DonationEntity) {
        initBottomSheet(donation)
    }

    override fun addToFavorite(isFavorite: Boolean) {

    }

    override fun showEmptyMessage(show: Boolean) {
        if (show)
            tvEmptyMessage.show()
        else
            tvEmptyMessage.hide()
    }

    override fun donate(donationId: Int) {
        testPay()
    }

    override fun shareDonation() {
        val share = Intent()
        share.apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Ссылка на приложение")
        }
        startActivity(Intent.createChooser(share, null))
    }

    private fun initBottomSheet(donation: DonationEntity) {
        val dialog = BottomSheetDialog(requireContext())
        val bottomSheet: View =
            layoutInflater.inflate(R.layout.bottom_sheet_about_donation, view as ViewGroup, false)

        bottomSheet.apply {
            findViewById<TextView>(R.id.titleTvBs).text = donation.title
            findViewById<TextView>(R.id.descriptionTvBs).text = donation.description
        }

        dialog.setContentView(bottomSheet)
        dialog.show()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    private fun testPay() {
        val parameters = PaymentParameters(
            amount = Amount(BigDecimal.TEN, Currency.getInstance("RUB")),
            title = "Название товара",
            subtitle = "Описание товара",
            clientApplicationKey = "test_NzI5Mjc0ssjxwvoxlwsV_HJl6U9jLTzNCqmEvUhakIg",
            shopId = "729274",
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

        val ui = UiParameters(showLogo = false)

        val intent = Checkout.createTokenizeIntent(requireContext(), parameters, uiParameters = ui)
        startActivityForResult(intent, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123) {
            when (resultCode) {
                Activity.RESULT_OK -> data?.let {
                    Log.d("TAG", Checkout.createTokenizationResult(it).toString())
                }
                Activity.RESULT_CANCELED -> Toast.makeText(context, "fail", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initListeners() {
        swipeRefreshDonationActive.setOnRefreshListener { presenter.refreshData() }

//        parentFragment?.searchView?.setOnQueryTextFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                parentFragment?.toolbarTitle?.visibility = View.GONE
//                parentFragment?.searchView?.layoutParams = ConstraintLayout.LayoutParams(
//                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT
//                )
//            } else {
//                parentFragment?.toolbarTitle?.visibility = View.VISIBLE
//                parentFragment?.searchView?.layoutParams = ConstraintLayout.LayoutParams(
//                    ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT
//                )
//            }
//        }
//        parentFragment?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                adapter.filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                adapter.filter(newText)
//                return false
//            }
//        })
    }

    inner class DividerItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 16
            }
        }
    }
}
