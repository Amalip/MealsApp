package com.amalip.exam2.presentation.mealDetail

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.usecase.GetMealById
import com.amalip.exam2.domain.usecase.GetRandomMeal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val getRandomMeal: GetRandomMeal,
    private val getMealById: GetMealById
) : BaseViewModel() {

    fun getRandomMeal() {
        getRandomMeal(UseCase.None()) {
            it.fold(::handleFailure, ::returnMeal)
        }
    }

    fun getMealById(id: Int) {
        getMealById(id) {
            it.fold(::handleFailure, ::returnMeal)
        }
    }

    private fun returnMeal(meal: Meal) {
        state.value = MealDetailViewState.MealReceived(meal)
    }

}