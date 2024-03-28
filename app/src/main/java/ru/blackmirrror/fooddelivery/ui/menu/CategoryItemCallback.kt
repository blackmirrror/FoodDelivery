package ru.blackmirrror.fooddelivery.ui.menu

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.fooddelivery.domain.models.Category

class CategoryItemCallback: ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.idCategory == newItem.idCategory
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}