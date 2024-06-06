package com.skintone.me.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skintone.me.R
import com.skintone.me.database.ImageSlider

class ImageSliderAdapter(private val sliderItems: List<ImageSlider>) :
    RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_slide, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sliderItem = sliderItems[position]
        holder.textView.text = sliderItem.title
        sliderItem.imageResId?.let {
            holder.imageView.setImageResource(it)
        }

        sliderItem.backgroundColor?.let {
            holder.frameLayout.setBackgroundResource(it)
        }
    }

    override fun getItemCount(): Int = sliderItems.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val frameLayout: FrameLayout = itemView.findViewById(R.id.frameLayout)
    }
}