package com.shamilov.payda.presentation.ui.donation.active.viewholder

import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.ui.donation.adapter.ImageSliderAdapter
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_donation_active.view.*
import java.util.*

/**
 * Created by Shamilov on 20.07.2020
 */
class DonationViewHolder(
    private val donation: DonationEntity,
    private val listener: DonationListener
) : Item() {

    private val adapter: ImageSliderAdapter by lazy { ImageSliderAdapter() }

    override fun getLayout(): Int {
        return R.layout.item_donation_active
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        with(viewHolder.itemView) {
            adapter.setData(donation.images)

            tvTitleActive.text = donation.title
            tvDescriptionActive.text = donation.description
            tvFundLocationActive.text = donation.region
            tvFundNameActive.text = context.getString(R.string.donation_fond, donation.fundName)
            tvAmountActive.text = donation.amount
            tvProgressActive.text = donation.progress

            vpImages.adapter = adapter
            TabLayoutMediator(tabsLayout, vpImages) { _, _ -> }
                .attach()
            Picasso.get()
                .load("https://hayra.ru/wp-content/uploads/2016/08/13627994_165961013828805_230291218_n.jpg")
                .into(ivFundLogoActive)

            setOnClickListener { listener.onItemClick(donation) }
            btnDonationHelp.setOnClickListener { listener.onDonateClick(donation.id) }
            btnShareDonation.setOnClickListener { listener.onShareClick() }
        }
    }

    interface DonationListener {
        fun onItemClick(donation: DonationEntity)
        fun onDonateClick(donationId: Int)
        fun onShareClick()
        fun onFavoriteClick()
    }

}