package ru.blackmirrror.fooddelivery.ui.menu

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.fooddelivery.domain.models.Meal

class MealItemCallback: ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.idMeal == newItem.idMeal
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}