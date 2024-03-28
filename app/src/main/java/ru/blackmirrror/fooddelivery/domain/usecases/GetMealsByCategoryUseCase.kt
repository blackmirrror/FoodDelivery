package ru.blackmirrror.fooddelivery.domain.usecases

import ru.blackmirrror.fooddelivery.domain.models.Meal
import ru.blackmirrror.fooddelivery.domain.models.ResultState
import ru.blackmirrror.fooddelivery.domain.repositories.MealRepository

class GetMealsByCategoryUseCase() {
    operator fun invoke(category: String, meals: List<Meal>): List<Meal> {
        return meals.filter { it.strCategory == category }
    }
}