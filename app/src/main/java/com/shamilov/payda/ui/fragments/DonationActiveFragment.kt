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
import com.google.android.material.appbar.AppBarLayout

import com.shamilov.payda.R
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.data.repository.DonationRepositoryImpl
import com.shamilov.payda.domain.repository.DonationRepository
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import com.shamilov.payda.ui.interfaces.OnDonationClickListener
import com.shamilov.payda.ui.presenters.DonationActivePresenter
import com.shamilov.payda.ui.views.DonationActiveView
import kotlinx.android.synthetic.main.fragment_active.*

class DonationActiveFragment : Fragment(), DonationActiveView, SwipeRefreshLayout.OnRefreshListener, OnDonationClickListener {

    val TAG = DonationActiveFragment::class.java.simpleName

    private lateinit var presenter: DonationActivePresenter
    private lateinit var adapter: DonationActiveAdapter
    private lateinit var repository: DonationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
        appBarLayout?.elevation = 0F

        swipeRefreshDonationActive.setOnRefreshListener(this)

        repository = DonationRepositoryImpl()
        adapter = DonationActiveAdapter(this)
        recyclerViewActive.adapter = adapter

        presenter = DonationActivePresenter(this, repository)
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
        adapter.setData(data)
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

    override fun onDonationClick(itemPosition: Int) {
        Toast.makeText(context, "item $itemPosition", Toast.LENGTH_SHORT).show()
    }

    override fun onDonationHelpClick(itemPosition: Int) {
        Toast.makeText(context, "button $itemPosition", Toast.LENGTH_SHORT).show()
    }
}
