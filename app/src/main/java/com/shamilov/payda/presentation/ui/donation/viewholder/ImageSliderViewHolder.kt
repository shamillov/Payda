package com.shamilov.payda.presentation.ui.donation.viewholder

import android.view.View
import coil.load
import com.shamilov.payda.R
import com.shamilov.payda.databinding.ItemImageBinding
import com.shamilov.payda.presentation.ui.donation.active.viewholder.DonationListener
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Shamilov on 25.05.2020
 */
class ImageSliderViewHolder(
    private val url: String,
    private val listener: DonationListener
    ) : BindableItem<ItemImageBinding>() {

    override fun getLayout() = R.layout.item_image

    override fun bind(viewBinding: ItemImageBinding, position: Int) {
        viewBinding.imageView.load(url) { crossfade(true) }
        viewBinding.imageView.setOnClickListener { listener.onImageClick() }
    }

    override fun initializeViewBinding(view: View) = ItemImageBinding.bind(view)
}