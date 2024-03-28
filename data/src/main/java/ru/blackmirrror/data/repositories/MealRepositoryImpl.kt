package ru.blackmirrror.fooddelivery.data.repositories

import android.content.Context
import ru.blackmirrror.data.local.FoodDb
import ru.blackmirrror.data.local.entity.MealEntity
import ru.blackmirrror.data.remote.ApiService
import ru.blackmirrror.domain.models.ClientError
import ru.blackmirrror.domain.models.Meal
import ru.blackmirrror.domain.models.NetworkUtils
import ru.blackmirrror.domain.models.NoContent
import ru.blackmirrror.domain.models.NoInternet
import ru.blackmirrror.domain.models.ResultState
import ru.blackmirrror.domain.models.ServerError
import ru.blackmirrror.domain.repositories.MealRepository

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