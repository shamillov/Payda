package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.images.view.*

/**
 * Created by Shamilov on 25.05.2020
 */
class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    var list: List<String> = listOf(
        "https://avatars.mds.yandex.net/get-pdb/216365/cafc6922-7989-4b22-b23d-36a495ce95a0/s1200",
        "https://avatars.mds.yandex.net/get-pdb/1604606/7393a5f6-4306-4e20-8f71-af8011c9e1a2/s1200",
        "https://avatars.mds.yandex.net/get-pdb/1976538/f5317206-7561-4a5a-9f26-22dd5d847fee/s1200"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        return ImageSliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.images,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        Picasso.get().load(list[position]).centerCrop().fit().into(holder.imageView)
    }

    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.imageView

    }
}