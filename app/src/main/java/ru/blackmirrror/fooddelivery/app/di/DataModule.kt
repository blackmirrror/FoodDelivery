package ru.blackmirrror.fooddelivery.app.di

import org.koin.dsl.module
import ru.blackmirrror.fooddelivery.data.api.ApiFactory
import ru.blackmirrror.fooddelivery.data.local.FoodDb
import ru.blackmirrror.fooddelivery.data.local.FoodDbFactory
import ru.blackmirrror.fooddelivery.data.remote.ApiService
import ru.blackmirrror.fooddelivery.data.repositories.CategoryRepositoryImpl
import ru.blackmirrror.fooddelivery.data.repositories.MealRepositoryImpl
import ru.blackmirrror.fooddelivery.domain.repositories.CategoryRepository
import ru.blackmirrror.fooddelivery.domain.repositories.MealRepository

val dataModule = module {
    single<MealRepository> {
        MealRepositoryImpl(
            context = get(),
            service = get(),
            database = get()
        )
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            context = get(),
            service = get(),
            database = get()
        )
    }

    single<ApiService> {
        ApiFactory.create()
    }

    single<FoodDb> {
        FoodDbFactory.create(get())
    }
}