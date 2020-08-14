package com.shamilov.payda.presentation.ui.donation.completed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationCompletedAdapter : RecyclerView.Adapter<DonationCompletedAdapter.DonationViewHolder>() {
    private val donationList: MutableList<DonationEntity> = ArrayList()

    fun setData(donationList: List<DonationEntity>) {
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
            //listeners
        }

        fun bind(donation: DonationEntity) {

        }
    }
}