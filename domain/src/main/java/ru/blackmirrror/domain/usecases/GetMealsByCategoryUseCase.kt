package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.models.Meal

class GetMealsByCategoryUseCase() {
    operator fun invoke(category: String, meals: List<Meal>): List<Meal> {
        return meals.filter { it.strCategory == category }
    }
}