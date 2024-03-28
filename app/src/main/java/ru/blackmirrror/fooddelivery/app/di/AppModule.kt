package ru.blackmirrror.fooddelivery.app.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.blackmirrror.fooddelivery.ui.menu.MenuViewModel

val appModule = module {
    viewModel {
        MenuViewModel(
            getMealsUseCase = get(),
            getCategoriesUseCase = get(),
            getBannersUseCase = get(),
            getMealsByCategoryUseCase = get()
        )
    }
}