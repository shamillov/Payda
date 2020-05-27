package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.data.model.DonationCompletedData
import com.shamilov.payda.ui.interfaces.OnDonationCompletedClickListener

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

    override fun getItemCount(): Int = donationList.count()

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donationList[position])
    }

    inner class DonationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { listener.onDonationClick(donationList[adapterPosition]) }
        }

        fun bind(donation: DonationCompletedData) {

        }
    }
}