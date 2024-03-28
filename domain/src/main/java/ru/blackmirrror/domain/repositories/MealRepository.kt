package ru.blackmirrror.domain.repositories

import ru.blackmirrror.domain.models.Meal
import ru.blackmirrror.domain.models.ResultState

interface MealRepository {
    suspend fun getMeals(): ResultState<List<Meal>>
}