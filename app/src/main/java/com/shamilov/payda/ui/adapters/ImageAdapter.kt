package com.shamilov.payda.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.payda.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.images.view.*

class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val imageList: List<String> = listOf("https://avatars.mds.yandex.net/get-pdb/1942669/c1340a42-a89a-412f-a9e4-295666560d00/s1200",
    "https://avatars.mds.yandex.net/get-pdb/2787500/ec554e7e-7242-45c1-a8af-4a72f6cbd2f6/s1200",
        "https://avatars.mds.yandex.net/get-pdb/1870458/34cf0962-d05e-480d-b003-150c2367abaf/s375")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.images, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = imageList.count()

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(imageList[position]).fit().centerCrop().into(holder.image)
    }

    inner class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.imageView
    }
}