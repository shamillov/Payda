package com.shamilov.payda.presentation.ui.donation.completed

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shamilov.common.base.BaseFragment
import moxy.ktx.moxyPresenter
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentCompletedBinding
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.extensions.gone
import com.shamilov.payda.extensions.visible
import com.shamilov.payda.presentation.ui.donation.completed.adapter.DonationCompletedAdapter

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedFragment : BaseFragment(R.layout.fragment_completed), DonationCompletedView,
    SwipeRefreshLayout.OnRefreshListener {

    private val TAG: String = DonationCompletedFragment::class.java.simpleName

    private var _binding: FragmentCompletedBinding? = null
    private val binding get() = _binding!!

    private val presenter: DonationCompletedPresenter by moxyPresenter { DonationCompletedPresenter() }
    private val adapter: DonationCompletedAdapter by lazy { DonationCompletedAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCompletedBinding.bind(view)

        binding.swipeRefreshDonationCompleted.setOnRefreshListener(this)

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
        binding.tvNetworkErrorCompleted.visible()
        binding.recyclerViewCompleted.gone()
    }

    override fun showNetworkError(showError: Boolean) {
        if (showError) {
            binding.tvNetworkErrorCompleted.visible()
            binding.recyclerViewCompleted.gone()
        } else {
            binding.tvNetworkErrorCompleted.gone()
            binding.recyclerViewCompleted.visible()
        }
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
            binding.progressBarCompleted.visible()
            binding.recyclerViewCompleted.gone()
        } else {
            binding.progressBarCompleted.gone()
            binding.recyclerViewCompleted.visible()
        }
    }

    override fun showSwipeLoading(loading: Boolean) {
        binding.swipeRefreshDonationCompleted.isRefreshing = loading
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onRefresh() {
        presenter.getData(isNetworkAvailable())
        binding.swipeRefreshDonationCompleted.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
