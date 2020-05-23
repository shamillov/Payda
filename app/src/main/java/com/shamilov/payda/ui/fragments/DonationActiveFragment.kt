package com.shamilov.payda.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout

import com.shamilov.payda.R
import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.repository.DonationRepositoryImpl
import com.shamilov.payda.domain.executor.SchedulerProvider
import com.shamilov.payda.domain.executor.SchedulerProviderImpl
import com.shamilov.payda.domain.repository.DonationRepository
import com.shamilov.payda.domain.interactor.GetActiveDonationUsecase
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import com.shamilov.payda.ui.adapters.ImageSwitcherAdapter
import com.shamilov.payda.ui.interfaces.OnDonationActiveClickListener
import com.shamilov.payda.ui.presenters.DonationActivePresenter
import com.shamilov.payda.ui.views.DonationActiveView
import kotlinx.android.synthetic.main.fragment_active.*

class DonationActiveFragment : Fragment(), DonationActiveView, SwipeRefreshLayout.OnRefreshListener, OnDonationActiveClickListener {

    val TAG = DonationActiveFragment::class.java.simpleName

    private lateinit var presenter: DonationActivePresenter
    private lateinit var adapter: DonationActiveAdapter
    private lateinit var repository: DonationRepository
    private lateinit var useCase: GetActiveDonationUsecase
    private lateinit var schedulers: SchedulerProvider
    private lateinit var mapper: DonationMapper

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

        initAdapter()
        init()
    }

    private fun init() {
        schedulers = SchedulerProviderImpl()
        mapper = DonationMapper()
        repository = DonationRepositoryImpl(mapper)
        useCase = GetActiveDonationUsecase(repository, schedulers)
        presenter = DonationActivePresenter(this, useCase)
        presenter.getData(isNetworkAvailable())
    }

    private fun initAdapter() {
        adapter = DonationActiveAdapter(this)
        recyclerViewActive.adapter = adapter
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

    override fun onSuccess(data: List<DonationActiveEntity>) {
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

    override fun onDonationClick(donation: DonationActiveEntity) {
        Toast.makeText(context, donation.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDonationHelpClick(donation: DonationActiveEntity) {
        Toast.makeText(context, donation.description, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
