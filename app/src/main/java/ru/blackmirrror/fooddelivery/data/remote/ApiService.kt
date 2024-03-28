package ru.blackmirrror.fooddelivery.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.blackmirrror.fooddelivery.domain.models.Categories
import ru.blackmirrror.fooddelivery.domain.models.Meals


interface ApiService {

    @GET("json/v1/1/search.php?s")
    suspend fun getMeals(): Response<Meals>

    @GET("json/v1/1/categories.php")
    suspend fun getCategories(): Response<Categories>
}