package ru.blackmirrror.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.blackmirrror.domain.models.Meal

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo("idMeal") var idMeal          : String = "",
    @ColumnInfo("strMeal"                     ) var strMeal                     : String? = null,
    @ColumnInfo("strCategory"                 ) var strCategory                 : String? = null,
    @ColumnInfo("strInstructions"             ) var strInstructions             : String? = null,
    @ColumnInfo("strMealThumb"                ) var strMealThumb                : String? = null,
) {
    companion object {
        fun fromResponseToEntity(response: Meal): MealEntity {
            return MealEntity(
                idMeal = response.idMeal,
                strMeal = response.strMeal,
                strCategory = response.strCategory,
                strInstructions = response.strInstructions,
                strMealThumb = response.strMealThumb
            )
        }

        fun fromEntityToResponse(entity: MealEntity): Meal {
            return Meal(
                idMeal = entity.idMeal,
                strMeal = entity.strMeal,
                strCategory = entity.strCategory,
                strInstructions = entity.strInstructions,
                strMealThumb = entity.strMealThumb
            )
        }
    }
}