package ru.blackmirrror.data.local

import android.content.Context
import androidx.room.Room

object FoodDbFactory {
    fun create(context: Context): FoodDb {
        return Room.databaseBuilder(
            context,
            FoodDb::class.java,
            "food_database"
        ).build()
    }
}