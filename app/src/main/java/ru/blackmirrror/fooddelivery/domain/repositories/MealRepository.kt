package ru.blackmirrror.fooddelivery.domain.repositories

import ru.blackmirrror.fooddelivery.domain.models.Meal
import ru.blackmirrror.fooddelivery.domain.models.ResultState

interface MealRepository {
    suspend fun getMeals(): ResultState<List<Meal>>
}