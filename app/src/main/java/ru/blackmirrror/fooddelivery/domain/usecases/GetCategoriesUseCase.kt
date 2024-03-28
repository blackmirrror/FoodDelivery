package ru.blackmirrror.fooddelivery.domain.usecases

import ru.blackmirrror.fooddelivery.domain.models.Category
import ru.blackmirrror.fooddelivery.domain.models.ResultState
import ru.blackmirrror.fooddelivery.domain.repositories.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): ResultState<List<Category>> {
        return categoryRepository.getCategories()
    }
}