package ru.blackmirrror.fooddelivery.ui.menu

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.domain.models.Banner

class BannerItemCallback: ItemCallback<Banner>() {
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.idBanner == newItem.idBanner
    }

    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }
}