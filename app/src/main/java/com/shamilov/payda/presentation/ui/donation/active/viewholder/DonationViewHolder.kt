package com.shamilov.payda.presentation.ui.donation.active.viewholder

import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.payda.R
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.base.BaseViewHolder
import com.shamilov.payda.presentation.ui.donation.adapter.ImageSliderAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_donation_active.view.*
import java.util.*

class DonationViewHolder(
    itemView: View,
    private val listener: (DonationEntity) -> Unit,
    private val donateListener: (Int) -> Unit,
    private val shareListener: () -> Unit,
    private val addToFavoriteListener: (Boolean) -> Unit
) : BaseViewHolder(itemView) {

    private val adapter: ImageSliderAdapter by lazy { ImageSliderAdapter() }

    fun onBind(donation: DonationEntity) {
        itemView.vpImages.adapter = adapter
        TabLayoutMediator(itemView.tabsLayout, itemView.vpImages) { _, _ -> }
            .attach()
        adapter.setData(donation.images)

        itemView.tvTitleActive.text = donation.title
        itemView.tvDescriptionActive.text = donation.description
        itemView.tvFundLocationActive.text = donation.region
        itemView.tvFundNameActive.text = context.getString(R.string.donation_fond, donation.fundName)
        itemView.tvAmountActive.text = String.format(Locale.CANADA_FRENCH, "%,d", donation.amount)
        itemView.tvProgressActive.text = String.format(Locale.CANADA_FRENCH, "%,d", donation.progress)
        Picasso.get()
            .load("https://hayra.ru/wp-content/uploads/2016/08/13627994_165961013828805_230291218_n.jpg")
            .into(itemView.ivFundLogoActive)

        initListeners(donation)
    }

    private fun initListeners(donation: DonationEntity) {
        itemView.setOnClickListener { listener.invoke(donation) }
        itemView.btnDonationHelp.setOnClickListener { donateListener.invoke(donation.id) }
        itemView.btnShareDonation.setOnClickListener { shareListener.invoke() }
//      itemView.ivFavoriteActive.setOnClickListener { addToFavoriteListener.invoke(isClicked) }
    }
}