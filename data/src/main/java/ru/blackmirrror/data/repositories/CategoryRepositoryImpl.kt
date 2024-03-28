package ru.blackmirrror.data.repositories

import android.content.Context
import ru.blackmirrror.data.local.FoodDb
import ru.blackmirrror.data.local.entity.CategoryEntity
import ru.blackmirrror.data.remote.ApiService
import ru.blackmirrror.domain.models.Category
import ru.blackmirrror.domain.models.ClientError
import ru.blackmirrror.domain.models.NetworkUtils
import ru.blackmirrror.domain.models.NoContent
import ru.blackmirrror.domain.models.NoInternet
import ru.blackmirrror.domain.models.ResultState
import ru.blackmirrror.domain.models.ServerError
import ru.blackmirrror.domain.repositories.CategoryRepository

class CategoryRepositoryImpl(
    private val context: Context,
    private val service: ApiService,
    private val database: FoodDb
): CategoryRepository {
    override suspend fun getCategories(): ResultState<List<Category>> {
        return if (NetworkUtils.isInternetConnected(context)) {
            val response = service.getCategories()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    database.categoryDao().insertCategories(
                        body.categories.map { CategoryEntity.fromResponseToEntity(it) }
                    )
                    ResultState.Success(body.categories)
                }
                else
                    ResultState.Error(NoContent, getCategoriesLocal())
            }
            else if (response.code() in 400..499)
                ResultState.Error(ClientError, getCategoriesLocal())
            else
                ResultState.Error(ServerError, getCategoriesLocal())
        } else {
            ResultState.Error(NoInternet, getCategoriesLocal())
        }
    }

    private suspend fun getCategoriesLocal(): List<Category> {
        return database.categoryDao().getAllCategories()
            .map { CategoryEntity.fromEntityToResponse(it) }
    }
}