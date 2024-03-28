package ru.blackmirrror.fooddelivery.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.blackmirrror.fooddelivery.domain.models.Banner
import ru.blackmirrror.fooddelivery.domain.models.Category
import ru.blackmirrror.fooddelivery.domain.models.ClientError
import ru.blackmirrror.fooddelivery.domain.models.Meal
import ru.blackmirrror.fooddelivery.domain.models.NoContent
import ru.blackmirrror.fooddelivery.domain.models.NoInternet
import ru.blackmirrror.fooddelivery.domain.models.ResultState
import ru.blackmirrror.fooddelivery.domain.models.ServerError
import ru.blackmirrror.fooddelivery.domain.usecases.GetBannersUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetCategoriesUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetMealsByCategoryUseCase
import ru.blackmirrror.fooddelivery.domain.usecases.GetMealsUseCase

class MenuViewModel(
    private val getMealsUseCase: GetMealsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getBannersUseCase: GetBannersUseCase,
    private val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {

    private val _allMeals = MutableLiveData<List<Meal>?>()

    private val _meals = MutableLiveData<List<Meal>?>()
    val meals: LiveData<List<Meal>?> = _meals

    private val _categories = MutableLiveData<List<Category>?>()
    val categories: LiveData<List<Category>?> = _categories

    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>> = _banners

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        getBanners()
        getCategories()
        getMeals()
    }

    private fun getBanners() {
        _banners.value = getBannersUseCase.invoke()
    }

    private fun getCategories() {
        viewModelScope.launch {
            _loading.postValue(true)
            when (val result = getCategoriesUseCase.invoke()) {
                is ResultState.Success  -> _categories.postValue(result.data)
                else -> handleError(result, false)
            }
            _loading.postValue(false)
        }
    }

    private fun getMeals() {
        viewModelScope.launch {
            _loading.postValue(true)
            when (val result = getMealsUseCase.invoke()) {
                is ResultState.Success -> {
                    _meals.postValue(result.data)
                    _allMeals.postValue(result.data)
                }
                else -> handleError(result, true)
            }
            _loading.postValue(false)
        }
    }

    fun getMealsByCategory(category: String) {
        _meals.postValue(_allMeals.value?.let {
            getMealsByCategoryUseCase.invoke(
                category, it
            )
        })
    }

    fun changeCategoryState(category: Category) {
        _categories.value?.forEach { it.isClicked = (it == category) }
        _categories.value = _categories.value
    }

    private fun <T> handleError(result: ResultState<T>, isMeal: Boolean) {
        if (result is ResultState.Error) {
            when (result.error) {
                is NoInternet -> _error.postValue("Соединение потеряно, загружаем сохраненные данные")
                is NoContent -> _error.postValue("Пока здесь пусто")
                is ClientError -> _error.postValue("Проверьте правильность данных")
                is ServerError -> _error.postValue("Неполадки на сервере, уже работаем над этим")
            }
            if (isMeal) {
                _meals.postValue(result.data as List<Meal>)
                _allMeals.postValue(result.data as List<Meal>)
            }
            else _categories.postValue(result.data as List<Category>)
        }
    }
}