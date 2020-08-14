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
import com.shamilov.payda.presentation.ui.donation.adapter.ImageSliderAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_donation_active.view.*
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
) : RecyclerView.Adapter<DonationAdapter.DonationViewHolder>() {

    private val TAG: String = DonationAdapter::class.java.simpleName

    private val donations: MutableList<DonationEntity> = ArrayList()
    private val searchDonation: MutableList<DonationEntity> = ArrayList()

    fun setData(list: List<DonationEntity>) {
        donations.clear()
        searchDonation.clear()
        donations.addAll(list)
        searchDonation.addAll(donations)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false)
        return DonationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationViewHolder, position: Int) {
        holder.bind(searchDonation[position])
    }

    override fun getItemCount(): Int = searchDonation.count()

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

    inner class DonationViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var viewPager: ViewPager2 = itemView.ivContentActive
        var tabLayout: TabLayout = itemView.tabsLayout

        private val adapter: ImageSliderAdapter by lazy { ImageSliderAdapter() }

        fun bind(donation: DonationEntity) {
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager) { _, _ -> }
                .attach()

            adapter.setData(donation.images)

            itemView.tvTitleActive.text = donation.title
            itemView.tvDescriptionActive.text = donation.description
            itemView.tvFundLocationActive.text = donation.region
            itemView.tvFundNameActive.text = context.getString(R.string.donation_fond, donation.fundName)
            itemView.tvAmountActive.text =
                String.format(Locale.CANADA_FRENCH, "%,d", donation.amount)
            itemView.tvProgressActive.text =
                String.format(Locale.CANADA_FRENCH, "%,d", donation.progress)

            Picasso.get()
                .load("https://hayra.ru/wp-content/uploads/2016/08/13627994_165961013828805_230291218_n.jpg")
                .into(itemView.ivFundLogoActive)

            initListeners()
        }

        private fun initListeners() {
            itemView.setOnClickListener { listener.invoke(donations[adapterPosition]) }
            itemView.btnDonationHelp.setOnClickListener { donateClickListener.invoke(donations[adapterPosition].id) }
            itemView.btnShareDonation.setOnClickListener { shareListener.invoke() }
//            itemView.ivFavoriteActive.setOnClickListener { addToFavoriteListener.invoke(isClicked) }
        }
    }
}