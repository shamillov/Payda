package com.shamilov.payda.presentation.ui.donation.adapter

import android.view.View
import coil.load
import com.shamilov.payda.R
import com.shamilov.payda.databinding.ImagesBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Shamilov on 25.05.2020
 */
class ImageSliderViewHolder(
    private val url: String
) : BindableItem<ImagesBinding>() {

    override fun getLayout() = R.layout.images

    override fun bind(viewBinding: ImagesBinding, position: Int) {
        viewBinding.imageView.load(url) { crossfade(true) }
    }

    override fun initializeViewBinding(view: View) = ImagesBinding.bind(view)
}