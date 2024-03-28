package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.models.Meal
import ru.blackmirrror.domain.models.ResultState
import ru.blackmirrror.domain.repositories.MealRepository

class GetMealsUseCase(private val mealRepository: MealRepository) {
    suspend operator fun invoke(): ResultState<List<Meal>> {
        return mealRepository.getMeals()
    }
}