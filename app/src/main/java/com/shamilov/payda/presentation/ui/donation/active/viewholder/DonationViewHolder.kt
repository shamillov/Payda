package com.shamilov.payda.presentation.ui.donation.active.viewholder

import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayoutMediator
import com.shamilov.payda.R
import com.shamilov.payda.databinding.ItemDonationActiveBinding
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.presentation.ui.donation.viewholder.ImageSliderViewHolder
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem

/**
 * Created by Shamilov on 20.07.2020
 */
class DonationViewHolder(
    private val donation: DonationEntity,
    private val listener: DonationListener
) : BindableItem<ItemDonationActiveBinding>() {

    var list: List<String> = listOf(
        "https://chechnyatoday.com/images/uploads/2018/09/26/IMG-20180925-WA0021.jpg",
        "https://www.grozny-inform.ru/LoadedImages/2015/07/12/foto_1.jpg",
        "https://gdb.rferl.org/68534DE4-6A6C-440A-82C2-918F22846678_w1023_r1_s.jpg"
    )

    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayout() = R.layout.item_donation_active

    override fun bind(viewBinding: ItemDonationActiveBinding, position: Int) {
        setImageItemAdapter()

        with(viewBinding) {
            tvTitleActive.text = donation.title
            tvDescriptionActive.text = donation.description
            tvFundLocationActive.text = donation.region
            tvFundNameActive.text = root.context.getString(R.string.donation_fond, donation.fundName)
            tvAmountActive.text = donation.amount
            tvProgressActive.text = donation.progress
            vpImages.adapter = adapter
            btnFavorite.isChecked = donation.isFavorite
            ivFundLogoActive.load("https://hayra.ru/wp-content/uploads/2016/08/13627994_165961013828805_230291218_n.jpg") {
                crossfade(true)
                transformations(CircleCropTransformation())
            }
            cvDonationActive.setOnClickListener { listener.onItemClick(donation) }
            btnDonationHelp.setOnClickListener { listener.onDonateClick(donation.id) }
            btnShareDonation.setOnClickListener { listener.onShareClick() }
            btnFavorite.setOnCheckedChangeListener { _, isChecked ->
                listener.onFavoriteClick(isChecked, donation.id)
            }

            if (list.size > 1) {
                TabLayoutMediator(tabsLayout, vpImages) { _, _ -> }.attach()
            }
        }

    }

    private fun setImageItemAdapter() {
        val items = mutableListOf<Group>().apply {
            addAll(list.map { ImageSliderViewHolder(it, listener) })
        }
        adapter.update(items)
    }

    override fun initializeViewBinding(view: View) = ItemDonationActiveBinding.bind(view)
}

interface DonationListener {
    fun onItemClick(donation: DonationEntity)
    fun onDonateClick(id: Int)
    fun onShareClick()
    fun onFavoriteClick(isFavorite: Boolean, id: Int)
    fun onImageClick()
}