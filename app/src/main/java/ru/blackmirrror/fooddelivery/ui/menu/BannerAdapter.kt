package ru.blackmirrror.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.domain.models.Banner
import ru.blackmirrror.fooddelivery.R

class BannerAdapter: ListAdapter<Banner, BannerAdapter.BannerViewHolder>(BannerItemCallback()) {

    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.iv_item_banner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val banner = getItem(position)
        holder.image.setImageResource(banner.image)
    }
}