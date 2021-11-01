package com.amalip.exam2.presentation.mealDetail

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.Meal

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class MealDetailViewState : BaseViewState() {

    data class MealReceived(val meal: Meal) : BaseViewState()

}