package ru.blackmirrror.fooddelivery.domain.repositories

import ru.blackmirrror.fooddelivery.domain.models.Category
import ru.blackmirrror.fooddelivery.domain.models.ResultState

interface CategoryRepository {
    suspend fun getCategories(): ResultState<List<Category>>
}