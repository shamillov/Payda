package com.shamilov.payda.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.shamilov.payda.R
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import com.shamilov.payda.ui.interfaces.OnDonationClickListener
import com.shamilov.payda.ui.presenters.DonationCompletedPresenter
import com.shamilov.payda.ui.views.DonationCompletedView
import kotlinx.android.synthetic.main.fragment_completed.*

class DonationCompletedFragment : Fragment(), DonationCompletedView, SwipeRefreshLayout.OnRefreshListener, OnDonationClickListener {

    private val TAG: String = DonationCompletedFragment::class.java.simpleName

    private lateinit var presenter: DonationCompletedPresenter
    private lateinit var adapter: DonationActiveAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshDonationCompleted.setOnRefreshListener(this)

        adapter = DonationActiveAdapter(this)
        recyclerViewCompleted.adapter = adapter

        presenter = DonationCompletedPresenter(this)
        presenter.getData(isNetworkAvailable())
    }

    override fun showProgressBar() {
        progressBarCompleted.visibility = View.VISIBLE
        recyclerViewCompleted.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBarCompleted.visibility = View.GONE
        recyclerViewCompleted.visibility = View.VISIBLE
    }

    override fun onSuccess(data: List<Donation>) {
        adapter.setData(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {

    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onRefresh() {
        presenter.getData(isNetworkAvailable())
        swipeRefreshDonationCompleted.isRefreshing = false
    }

    override fun onDonationClick(itemPosition: Int) {

    }

    override fun onDonationHelpClick(itemPosition: Int) {

    }
}
