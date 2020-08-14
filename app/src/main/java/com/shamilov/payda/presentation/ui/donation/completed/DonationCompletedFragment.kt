package com.shamilov.payda.presentation.ui.donation.completed

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import moxy.ktx.moxyPresenter

import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseFragment
import com.shamilov.payda.presentation.ui.donation.completed.adapter.DonationCompletedAdapter
import kotlinx.android.synthetic.main.fragment_completed.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedFragment : BaseFragment(R.layout.fragment_completed),
    DonationCompletedView,
    SwipeRefreshLayout.OnRefreshListener {

    private val TAG: String = DonationCompletedFragment::class.java.simpleName

    private val presenter: DonationCompletedPresenter by moxyPresenter { DonationCompletedPresenter() }
    private val adapter: DonationCompletedAdapter by lazy { DonationCompletedAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshDonationCompleted.setOnRefreshListener(this)

        init()
    }

    private fun init() {
        presenter.getData(isNetworkAvailable())
    }

    override fun onSuccess(data: List<DonationEntity>) {
        adapter.setData(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        tvNetworkErrorCompleted.visibility = View.VISIBLE
        recyclerViewCompleted.visibility = View.GONE
    }

    override fun showNetworkError(hasNetwork: Boolean) {
        if (hasNetwork) {
            tvNetworkErrorCompleted.visibility = View.VISIBLE
            recyclerViewCompleted.visibility = View.GONE
        } else {
            tvNetworkErrorCompleted.visibility = View.GONE
            recyclerViewCompleted.visibility = View.VISIBLE
        }
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
            progressBarCompleted.visibility = View.VISIBLE
            recyclerViewCompleted.visibility = View.GONE
        } else {
            progressBarCompleted.visibility = View.GONE
            recyclerViewCompleted.visibility = View.VISIBLE
        }
    }

    override fun showSwipeLoading(loading: Boolean) {
        swipeRefreshDonationCompleted.isRefreshing = loading
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onRefresh() {
        presenter.getData(isNetworkAvailable())
        swipeRefreshDonationCompleted.isRefreshing = false
    }
}
