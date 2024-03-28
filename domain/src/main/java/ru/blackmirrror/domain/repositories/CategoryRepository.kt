package ru.blackmirrror.domain.repositories

import ru.blackmirrror.domain.models.Category
import ru.blackmirrror.domain.models.ResultState

interface CategoryRepository {
    suspend fun getCategories(): ResultState<List<Category>>
}