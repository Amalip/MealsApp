package com.amalip.exam2.presentation.meals

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.Meal

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class MealsViewState : BaseViewState() {

    data class MealsReceived(val meals: List<Meal>) : BaseViewState()
    data class MealChanged(val meal: Meal): BaseViewState()

}