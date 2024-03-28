package ru.blackmirrror.domain.usecases

import ru.blackmirrror.domain.models.Category
import ru.blackmirrror.domain.models.ResultState
import ru.blackmirrror.domain.repositories.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {
    suspend operator fun invoke(): ResultState<List<Category>> {
        return categoryRepository.getCategories()
    }
}