package com.shamilov.payda.presentation.ui.funds.viewholder

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.shamilov.payda.R
import com.shamilov.payda.databinding.ItemFundsBinding
import com.shamilov.payda.domain.model.FundEntity
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Shamilov on 29.12.2020
 */
class FundViewHolder(
    private val fund: FundEntity,
    private val listener: OnFundClickListener
) : BindableItem<ItemFundsBinding>() {
    override fun bind(binding: ItemFundsBinding, position: Int) {
        binding.root.setOnClickListener { listener.onFundClick(fund.id) }
        binding.ivLogo.load(fund.logo.name) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        binding.tvName.text = fund.name
        binding.tvRegion.text = fund.region
    }

    override fun getLayout() = R.layout.item_funds
    override fun initializeViewBinding(view: View) = ItemFundsBinding.bind(view)
}

interface OnFundClickListener {
    fun onFundClick(id: Int)
}