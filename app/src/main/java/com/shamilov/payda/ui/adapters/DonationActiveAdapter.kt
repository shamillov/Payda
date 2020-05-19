package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.data.models.Donation
import kotlinx.android.synthetic.main.item_donation_active.view.*

class DonationActiveAdapter(private var list: List<Donation>):
    RecyclerView.Adapter<DonationActiveAdapter.DonationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        val donation = list[position]
        holder.textView.text = donation.city
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DonationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.tvFundLocationActive
    }
}