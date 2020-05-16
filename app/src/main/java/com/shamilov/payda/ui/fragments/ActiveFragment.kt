package com.shamilov.payda.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.shamilov.payda.R
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.ui.adapters.DonationActiveAdapter
import com.shamilov.payda.ui.presenters.ActivePresenter
import com.shamilov.payda.ui.views.ActiveView
import kotlinx.android.synthetic.main.fragment_active.*

class ActiveFragment : Fragment(), ActiveView {

    private lateinit var presenter: ActivePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_active, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerViewActive.layoutManager = LinearLayoutManager(context)
        presenter = ActivePresenter(this)
        presenter.getDonation()
    }

    override fun showProgressBar() {
        progressBarActive.visibility = View.VISIBLE
        recyclerViewActive.visibility = View.GONE
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

    override fun onDestroy() {
        super.onDestroy()
    }

}
