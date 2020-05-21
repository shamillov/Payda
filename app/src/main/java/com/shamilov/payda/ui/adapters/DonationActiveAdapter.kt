package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.data.models.Donation
import com.shamilov.payda.ui.interfaces.OnDonationClickListener
import kotlinx.android.synthetic.main.item_donation_active.view.*

class DonationActiveAdapter(private val donationClickListener: OnDonationClickListener):
    RecyclerView.Adapter<DonationActiveAdapter.DonationViewHolder>() {
    private val donationList: MutableList<Donation> = ArrayList()

    fun setData(donationList: List<Donation>) {
        this.donationList.clear()
        this.donationList.addAll(donationList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donationList[position])
    }

    override fun getItemCount(): Int {
        return donationList.count()
    }

    inner class DonationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.tvFundLocationActive

        init {
            itemView.setOnClickListener { donationClickListener.onDonationClick(adapterPosition) }
            itemView.btnDonationHelp.setOnClickListener { donationClickListener.onDonationHelpClick(adapterPosition) }
        }

        fun bind(donation: Donation) {
            textView.text = donation.fundLocation
        }
    }
}