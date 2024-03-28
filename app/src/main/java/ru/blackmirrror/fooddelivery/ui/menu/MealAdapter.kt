package ru.blackmirrror.fooddelivery.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.button.MaterialButton
import ru.blackmirrror.domain.models.Meal
import ru.blackmirrror.fooddelivery.R

class MealAdapter: ListAdapter<Meal, MealAdapter.MealViewHolder>(MealItemCallback()) {

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.iv_item_food)
        val title = itemView.findViewById<TextView>(R.id.tv_item_food_title)
        val description = itemView.findViewById<TextView>(R.id.tv_item_food_description)
        val btnCart = itemView.findViewById<MaterialButton>(R.id.btn_item_food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = getItem(position)
        with(holder) {
            title.text = meal.strMeal
            description.text = meal.strInstructions // не было описания, только рецепт
            image.load(meal.strMealThumb)
            btnCart.text = "от 350 р" // не было цены
        }
    }
}