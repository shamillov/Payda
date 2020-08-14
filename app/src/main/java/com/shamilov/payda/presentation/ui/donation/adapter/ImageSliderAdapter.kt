package com.shamilov.payda.presentation.ui.donation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.shamilov.payda.R
import com.shamilov.payda.data.model.ImageData
import com.shamilov.payda.domain.model.ImageEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.images.view.*

/**
 * Created by Shamilov on 25.05.2020
 */
class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder>() {

    var list: List<String> = listOf(
        "https://chechnyatoday.com/images/uploads/2018/09/26/IMG-20180925-WA0021.jpg",
        "https://www.grozny-inform.ru/LoadedImages/2015/07/12/foto_1.jpg",
        "https://gdb.rferl.org/68534DE4-6A6C-440A-82C2-918F22846678_w1023_r1_s.jpg"
    )

    private var imageList: MutableList<ImageEntity> = ArrayList()

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
        holder.bind(list[position])
    }

    fun setData(images: List<ImageEntity>) {
        imageList.clear()
        imageList.addAll(images)
    }

    inner class ImageSliderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.imageView

        fun bind(image: String) {
            val progress = CircularProgressDrawable(itemView.context)
            progress.apply {
                strokeWidth = 5f
                centerRadius = 30f
                start()
            }

            Picasso.get().load(image).centerCrop().fit().placeholder(progress).into(imageView)
        }
    }
}