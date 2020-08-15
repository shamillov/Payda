package com.shamilov.payda.presentation.ui.donation.active.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseViewHolder
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationViewHolder
import com.shamilov.payda.presentation.ui.donation.active.viewholder.HeaderViewHolder
import com.shamilov.payda.presentation.ui.donation.adapter.ImageSliderAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_donation_active.view.*
import java.lang.IllegalStateException
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationAdapter(
    private val listener: (DonationEntity) -> Unit,
    private val donateClickListener: (Int) -> Unit,
    private val shareListener: () -> Unit,
    private val addToFavoriteListener: (Boolean) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = DonationAdapter::class.java.simpleName

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_DONATION = 1
    }

    private val donations: MutableList<DonationEntity> = ArrayList()
    private val searchDonation: MutableList<DonationEntity> = ArrayList()

    fun setData(list: List<DonationEntity>) {
        donations.clear()
        searchDonation.clear()
        donations.addAll(list)
        searchDonation.addAll(donations)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_help_us, parent, false)
            )
            TYPE_DONATION -> DonationViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false),
                listener,
                donateClickListener,
                shareListener,
                addToFavoriteListener
            )
            else -> throw IllegalStateException("fail")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.onBind()
            is DonationViewHolder -> holder.onBind(searchDonation[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER
        else TYPE_DONATION
    }

    override fun getItemCount(): Int = searchDonation.size

    fun filter(query: String) {
        searchDonation.clear()
        searchDonation.addAll(
            donations.filter {
                it.fundName.contains(query, ignoreCase = true)
                        || it.title.contains(query, ignoreCase = true)
                        || it.region.contains(query, ignoreCase = true)
            }
        )
        notifyDataSetChanged()
    }
}