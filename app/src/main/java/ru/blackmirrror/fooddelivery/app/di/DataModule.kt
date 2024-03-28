package ru.blackmirrror.fooddelivery.app.di

import org.koin.dsl.module
import ru.blackmirrror.data.api.ApiFactory
import ru.blackmirrror.data.local.FoodDb
import ru.blackmirrror.data.local.FoodDbFactory
import ru.blackmirrror.data.remote.ApiService
import ru.blackmirrror.data.repositories.CategoryRepositoryImpl
import ru.blackmirrror.domain.repositories.CategoryRepository
import ru.blackmirrror.domain.repositories.MealRepository
import ru.blackmirrror.fooddelivery.data.repositories.MealRepositoryImpl

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