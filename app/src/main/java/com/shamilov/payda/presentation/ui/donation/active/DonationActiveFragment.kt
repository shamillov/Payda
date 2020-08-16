package com.shamilov.payda.presentation.ui.donation.active

import android.app.ActionBar
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.ybq.android.spinkit.style.Circle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseFragment
import com.shamilov.payda.presentation.ui.donation.active.adapter.DonationAdapter
import kotlinx.android.synthetic.main.fragment_active.*
import kotlinx.android.synthetic.main.fragment_home.*
import moxy.ktx.moxyPresenter
import ru.yandex.money.android.sdk.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActiveFragment : BaseFragment(R.layout.fragment_active), DonationActiveView,
    SwipeRefreshLayout.OnRefreshListener {

    private val TAG = DonationActiveFragment::class.java.simpleName

    private val presenter by moxyPresenter { DonationActivePresenter() }

    private val adapter by lazy {
        DonationAdapter(
            { presenter.donationClicked(it) },
            { presenter.helpClicked(it) },
            { presenter.shareClicked() },
            { presenter.favoriteClicked(it) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initAdapter()
        initListeners()
        presenter.loadData(isNetworkAvailable())
    }

    private fun initAdapter() {
        recyclerViewActive.setHasFixedSize(true)
        recyclerViewActive.adapter = adapter
        recyclerViewActive.addItemDecoration(DividerItemDecoration())
        recyclerViewActive.layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
    }

    private fun initViews() {
        swipeRefreshDonationActive.setColorSchemeColors(context?.let {
            ContextCompat.getColor(it, R.color.colorPrimaryDark)
        }!!)

        val doubleBounce = Circle()
        progressBarActive.setIndeterminateDrawable(doubleBounce)
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
            progressBarActive.visibility = View.VISIBLE
            recyclerViewActive.visibility = View.GONE
        } else {
            progressBarActive.visibility = View.GONE
            recyclerViewActive.visibility = View.VISIBLE
        }
    }

    override fun showSwipeLoading(loading: Boolean) {
        swipeRefreshDonationActive.isRefreshing = loading
    }

    override fun showNetworkError(showError: Boolean) {
        if (showError) {
            tvNetworkErrorActive.visibility = View.VISIBLE
            recyclerViewActive.visibility = View.GONE
        } else {
            tvNetworkErrorActive.visibility = View.GONE
            recyclerViewActive.visibility = View.VISIBLE
        }
    }

    override fun onSuccess(data: List<DonationEntity>) {
        adapter.setData(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        presenter.refreshData()
    }

    override fun openDonation(donation: DonationEntity) {
        initBottomSheet(donation)
    }

    override fun addToFavorite(isFavorite: Boolean) {

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
        swipeRefreshDonationActive.setOnRefreshListener(this)
        parentFragment?.searchView?.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                parentFragment?.toolbarTitle?.visibility = View.GONE
                parentFragment?.searchView?.layoutParams = ConstraintLayout.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT
                )
            } else {
                parentFragment?.toolbarTitle?.visibility = View.VISIBLE
                parentFragment?.searchView?.layoutParams = ConstraintLayout.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT
                )
            }
        }
        parentFragment?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                adapter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter(newText)
                return false
            }
        })
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
