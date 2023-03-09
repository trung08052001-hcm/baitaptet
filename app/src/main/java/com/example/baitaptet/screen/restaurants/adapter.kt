package com.example.baitaptet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.baitaptet.screen.restaurants.Image
import com.example.baitaptet.screen.restaurants.ImageViewHolder


interface OnImageItemListener {
    fun onClickItem(item: Image)

    fun onLongClick(item: Image)
}

class ImageAdapter(val itemListener: OnImageItemListener) :
    ListAdapter<Image, ImageViewHolder>
        (ImageDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_image, parent, false)
        val viewHolder = ImageViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bindData(image, itemListener)


        holder.itemView.setOnClickListener {

            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { dialog, which ->
                    // delete item from adapter
                    val newList = currentList.toMutableList()
                    newList.removeAt(position)
                    submitList(newList)
                }
                .setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }
            builder.show()
        }
    }


    class ImageDiffUtil : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.name == newItem.name && oldItem.link == newItem.link
        }

    }

}