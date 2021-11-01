package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.repository.MealRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/30/2021.
 */

class GetMealsByCategory @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<List<Meal>, String>() {

    override suspend fun run(params: String) = mealRepository.getMealsByCategory(params)

}