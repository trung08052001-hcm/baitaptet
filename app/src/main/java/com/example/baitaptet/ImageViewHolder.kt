package com.example.baitaptet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view =
                layoutInflater.inflate(
                    R.layout.item_view_image,
                    parent,
                    false
                )
            return ImageViewHolder(view)
        }
    }

    fun bindData(image: Image, callback: OnImageItemListener) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title);
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val ivAvatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
        tvTitle.text = image.name
        tvDescription.text = image.address
        Glide.with(itemView.context).load(image.link).centerCrop().into(ivAvatar)
        itemView.setOnClickListener { callback.onClickItem(image) }
        itemView.setOnLongClickListener {
            callback.onLongClick(image)
            true
        }
    }
}