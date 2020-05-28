package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.ui.interfaces.OnDonationCompletedClickListener
import kotlinx.android.synthetic.main.item_donation_completed.view.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedAdapter(private val listener: OnDonationCompletedClickListener) :
    RecyclerView.Adapter<DonationCompletedAdapter.DonationViewHolder>() {
    private val donationList: MutableList<DonationCompletedData> = ArrayList()

    fun setData(donationList: List<DonationCompletedData>) {
        this.donationList.clear()
        this.donationList.addAll(donationList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_donation_active, parent, false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donationList[position])
    }

    override fun getItemCount(): Int = donationList.count()

    inner class DonationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { listener.onDonationClick(donationList[adapterPosition]) }
            itemView.btnShareDonation.setOnClickListener { listener.onShareClick() }
        }

        fun bind(donation: DonationCompletedData) {

        }
    }
}