package ru.blackmirrror.fooddelivery.app.di

import org.koin.dsl.module
import ru.blackmirrror.domain.usecases.GetBannersUseCase
import ru.blackmirrror.domain.usecases.GetCategoriesUseCase
import ru.blackmirrror.domain.usecases.GetMealsByCategoryUseCase
import ru.blackmirrror.domain.usecases.GetMealsUseCase

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