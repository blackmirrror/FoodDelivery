package ru.blackmirrror.fooddelivery.ui.menu

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.fooddelivery.R
import ru.blackmirrror.fooddelivery.domain.models.Category

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(CategoryItemCallback()) {

    var onCategoryItemClickListener: ((Category) -> Unit)? = null

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.btn_item_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.title.text = category.strCategory
        holder.itemView.setOnClickListener {
            onCategoryItemClickListener?.invoke(category)
        }
    }
}