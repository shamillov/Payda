package com.shamilov.payda.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shamilov.payda.App
import com.shamilov.payda.R
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.ui.adapters.DonationCompletedAdapter
import com.shamilov.payda.ui.interfaces.OnDonationCompletedClickListener
import com.shamilov.payda.ui.presenters.DonationCompletedPresenter
import com.shamilov.payda.ui.views.DonationCompletedView
import kotlinx.android.synthetic.main.fragment_completed.*
import javax.inject.Inject

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedFragment : Fragment(), DonationCompletedView,
    SwipeRefreshLayout.OnRefreshListener,
    OnDonationCompletedClickListener {

    private val TAG: String = DonationCompletedFragment::class.java.simpleName

    @Inject
    lateinit var presenter: DonationCompletedPresenter
    private lateinit var adapter: DonationCompletedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_completed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshDonationCompleted.setOnRefreshListener(this)

        initAdapter()
        init()
    }

    private fun init() {
        presenter.getData(isNetworkAvailable())
    }

    private fun initAdapter() {
        adapter = DonationCompletedAdapter(this)
        recyclerViewCompleted.adapter = adapter
    }

    override fun showProgressBar() {
        progressBarCompleted.visibility = View.VISIBLE
        recyclerViewCompleted.visibility = View.GONE
    }

    override fun hideProgressBar() {
        progressBarCompleted.visibility = View.GONE
        recyclerViewCompleted.visibility = View.VISIBLE
    }

    override fun onSuccess(data: List<DonationCompletedData>) {
        adapter.setData(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun showNetworkError() {
        tvNetworkErrorCompleted.visibility = View.VISIBLE
        recyclerViewCompleted.visibility = View.GONE
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onRefresh() {
        presenter.getData(isNetworkAvailable())
        swipeRefreshDonationCompleted.isRefreshing = false
    }

    override fun onDonationClick(donation: DonationCompletedData) {

    }

    override fun onDonationHelpClick(donation: DonationCompletedData) {

    }

    override fun onShareClick() {

    }
}
