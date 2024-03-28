package ru.blackmirrror.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.blackmirrror.data.local.entity.MealEntity

@Dao
interface MealDao {
    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeals(meals: List<MealEntity>)
    @Delete
    suspend fun deleteMeals(meals: List<MealEntity>)
}