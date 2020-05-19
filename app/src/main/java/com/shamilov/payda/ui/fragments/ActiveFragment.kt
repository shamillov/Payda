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
import com.shamilov.payda.ui.presenters.ActivePresenter
import com.shamilov.payda.ui.views.ActiveView
import kotlinx.android.synthetic.main.fragment_active.*

class ActiveFragment : Fragment(), ActiveView, SwipeRefreshLayout.OnRefreshListener {

    val TAG = ActiveFragment::class.java.simpleName

    private lateinit var presenter: ActivePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshDonationActive.setOnRefreshListener(this)
        presenter = ActivePresenter(this)
        presenter.getData(isNetworkAvailable())
    }

    override fun showProgressBar() {
        progressBarActive.visibility = View.VISIBLE
        recyclerViewActive.visibility = View.GONE
        tvNetworkErrorActive.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBarActive.visibility = View.GONE
        recyclerViewActive.visibility = View.VISIBLE
    }

    override fun onSuccess(data: List<Donation>) {
        recyclerViewActive.adapter = DonationActiveAdapter(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        tvNetworkErrorActive.visibility = View.VISIBLE
        recyclerViewActive.visibility = View.GONE
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onRefresh() {
        presenter.getData(isNetworkAvailable())
        swipeRefreshDonationActive.isRefreshing = false
    }
}
