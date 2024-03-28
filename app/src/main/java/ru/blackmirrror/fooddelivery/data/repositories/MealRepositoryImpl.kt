package ru.blackmirrror.fooddelivery.data.repositories

import android.content.Context
import ru.blackmirrror.fooddelivery.data.local.FoodDb
import ru.blackmirrror.fooddelivery.data.local.entity.CategoryEntity
import ru.blackmirrror.fooddelivery.data.local.entity.MealEntity
import ru.blackmirrror.fooddelivery.data.remote.ApiService
import ru.blackmirrror.fooddelivery.domain.models.Category
import ru.blackmirrror.fooddelivery.domain.models.ClientError
import ru.blackmirrror.fooddelivery.domain.models.Meal
import ru.blackmirrror.fooddelivery.domain.models.NetworkUtils
import ru.blackmirrror.fooddelivery.domain.models.NoContent
import ru.blackmirrror.fooddelivery.domain.models.NoInternet
import ru.blackmirrror.fooddelivery.domain.models.ResultState
import ru.blackmirrror.fooddelivery.domain.models.ServerError
import ru.blackmirrror.fooddelivery.domain.repositories.MealRepository

class MealRepositoryImpl(
    private val context: Context,
    private val service: ApiService,
    private val database: FoodDb
): MealRepository {
    override suspend fun getMeals(): ResultState<List<Meal>> {
        return if (NetworkUtils.isInternetConnected(context)) {
            val response = service.getMeals()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    database.mealDao().insertMeals(
                        body.meals.map { MealEntity.fromResponseToEntity(it) })
                    ResultState.Success(body.meals)
                }
                else
                    ResultState.Error(NoContent, getMealsLocal())
            }
            else if (response.code() in 400..499)
                ResultState.Error(ClientError, getMealsLocal())
            else
                ResultState.Error(ServerError, getMealsLocal())
        } else {
            ResultState.Error(NoInternet, getMealsLocal())
        }
    }

    private suspend fun getMealsLocal(): List<Meal> {
        return database.mealDao().getAllMeals()
            .map { MealEntity.fromEntityToResponse(it) }
    }
}