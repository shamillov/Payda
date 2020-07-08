package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.shamilov.payda.data.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.images.view.*

/**
 * Created by Shamilov on 25.05.2020
 */
class ImageSliderAdapter(private val imageList: List<Image>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    var list: List<String> = listOf(
        "https://chechnyatoday.com/images/uploads/2018/09/26/IMG-20180925-WA0021.jpg",
        "https://www.grozny-inform.ru/LoadedImages/2015/07/12/foto_1.jpg",
        "https://gdb.rferl.org/68534DE4-6A6C-440A-82C2-918F22846678_w1023_r1_s.jpg"
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
        Picasso.get().load(list[position]).centerCrop().fit()
            .placeholder(R.drawable.image_loading_animation).into(holder.imageView)
    }

    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.imageView

    }
}