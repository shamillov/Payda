package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationActiveEntity
import com.shamilov.payda.ui.interfaces.OnDonationActiveClickListener
import kotlinx.android.synthetic.main.item_donation_active.view.*

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActiveAdapter(private val listener: OnDonationActiveClickListener) :
    RecyclerView.Adapter<DonationActiveAdapter.DonationViewHolder>() {
    private val donationList: MutableList<DonationActiveEntity> = ArrayList()

    fun setData(list: List<DonationActiveEntity>) {
        this.donationList.clear()
        this.donationList.addAll(list)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_donation_active,
            parent,
            false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(donationList[position])
        holder.viewPager.adapter = ImageSliderAdapter()
    }

    override fun getItemCount(): Int = donationList.count()

    inner class DonationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitleActive: TextView = itemView.tvTitleActive
        var tvDescriptionActive: TextView = itemView.tvDescriptionActive
        var tvAmountActive: TextView = itemView.tvAmountActive
        var tvFundLocationActive: TextView = itemView.tvFundLocationActive
        var tvProgressActive: TextView = itemView.tvProgressActive
        var viewPager: ViewPager2 = itemView.ivContentActive

        init {
            itemView.setOnClickListener { listener.onDonationClick(donationList[adapterPosition]) }
            itemView.btnDonationHelp.setOnClickListener { listener.onDonationHelpClick(donationList[adapterPosition]) }
            itemView.btnShareDonation.setOnClickListener { listener.onShareClick() }
        }

        fun bind(donation: DonationActiveEntity) {
            tvTitleActive.text = donation.title
            tvDescriptionActive.text = donation.description
            tvAmountActive.text = donation.amount.toString()
            tvFundLocationActive.text = donation.location
            tvProgressActive.text = donation.progress.toString()
        }
    }
}