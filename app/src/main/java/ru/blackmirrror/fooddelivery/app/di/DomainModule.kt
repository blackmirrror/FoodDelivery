package ru.blackmirrror.fooddelivery.app.di

import org.koin.dsl.module
import ru.blackmirrror.fooddelivery.domain.usecases.GetBannersUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetCategoriesUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetMealsByCategoryUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetMealsUseCase

val domainModule = module {
    factory {
        GetMealsUseCase(mealRepository = get())
    }

    factory {
        GetCategoriesUseCase(categoryRepository = get())
    }

    factory {
        GetBannersUseCase()
    }

    factory {
        GetMealsByCategoryUseCase()
    }
}