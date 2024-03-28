package ru.blackmirrror.fooddelivery.domain.usecases

import ru.blackmirrror.fooddelivery.domain.models.Meal
import ru.blackmirrror.fooddelivery.domain.models.ResultState
import ru.blackmirrror.fooddelivery.domain.repositories.MealRepository

class GetMealsUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke(): ResultState<List<Meal>> {
        return mealRepository.getMeals()
    }
}