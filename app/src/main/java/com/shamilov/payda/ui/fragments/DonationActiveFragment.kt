package com.shamilov.payda.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.shamilov.payda.App
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.FeeEntity
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import com.shamilov.payda.ui.interfaces.OnDonationActiveClickListener
import com.shamilov.payda.ui.presenters.DonationActivePresenter
import com.shamilov.payda.ui.views.DonationActiveView
import kotlinx.android.synthetic.main.fragment_about_donation.*
import kotlinx.android.synthetic.main.fragment_active.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActiveFragment : MvpAppCompatFragment(), DonationActiveView, SwipeRefreshLayout.OnRefreshListener,
    OnDonationActiveClickListener {

    private val TAG = DonationActiveFragment::class.java.simpleName

    @Inject
    lateinit var presenterProvider: Provider<DonationActivePresenter>
    private val presenter by moxyPresenter {presenterProvider.get()}
    private lateinit var adapter: DonationActiveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
        appBarLayout?.elevation = 0F

        swipeRefreshDonationActive.setOnRefreshListener(this)
        swipeRefreshDonationActive.setColorSchemeColors(context?.let {
            ContextCompat.getColor(
                it,
                R.color.colorPrimaryDark
            )
        }!!)

        initAdapter()
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

    override fun onSuccess(data: List<FeeEntity>) {
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

    override fun onDonationClick(donation: FeeEntity) {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onDonationHelpClick(donation: FeeEntity) {
        Toast.makeText(context, donation.description, Toast.LENGTH_SHORT).show()
    }

    override fun onShareClick() {
        val share = Intent()
        share.apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Ссылка на приложение")
        }
        startActivity(Intent.createChooser(share, null))
    }

//    override fun onStop() {
//        presenter.onStop()
//        super.onStop()
//    }
}
