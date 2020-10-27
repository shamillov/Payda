package com.shamilov.payda.presentation.ui.donation.active.viewholder

import android.view.View
import com.shamilov.payda.R
import com.shamilov.payda.databinding.ItemDonationHeaderBinding
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Shamilov on 20.07.2020
 */
class HeaderViewHolder : BindableItem<ItemDonationHeaderBinding>() {

    override fun bind(viewBinding: ItemDonationHeaderBinding, position: Int) {

    }

    override fun getLayout() = R.layout.item_donation_header
    override fun initializeViewBinding(view: View) = ItemDonationHeaderBinding.bind(view)
}