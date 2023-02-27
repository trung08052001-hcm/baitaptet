package com.example.baitaptet

import android.graphics.ImageDecoder.OnPartialImageListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter



interface OnImageItemListener {
    fun onClickItem(item: Image)

    fun onLongClick(item: Image)
}

class ImageAdapter(val itemListener: OnImageItemListener) :
    ListAdapter<Image, ImageViewHolder>
        (ImageDiffUtil()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bindData(image, itemListener)
    }


    class ImageDiffUtil : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Image,
            newItem: Image
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.link == newItem.name
        }

    }

}