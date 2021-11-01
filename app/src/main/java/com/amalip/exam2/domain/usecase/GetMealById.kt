package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.repository.MealRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/30/2021.
 */

class GetMealById @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<Meal, Int>() {

    override suspend fun run(params: Int) = mealRepository.getMealDetailById(params)

}