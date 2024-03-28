package ru.blackmirrror.fooddelivery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.blackmirrror.fooddelivery.data.local.dao.CategoryDao
import ru.blackmirrror.fooddelivery.data.local.dao.MealDao
import ru.blackmirrror.fooddelivery.data.local.entity.CategoryEntity
import ru.blackmirrror.fooddelivery.data.local.entity.MealEntity

@Database(entities = [CategoryEntity::class, MealEntity::class], version = 1, exportSchema = true)
abstract class FoodDb : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun mealDao(): MealDao
}