package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.repository.MealRepository
import com.amalip.exam2.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/29/2021.
 */
class ExecuteLike @Inject constructor(private val mealRepository: MealRepository) :
    UseCase<Long, UserMealsLikes>() {

    override suspend fun run(params: UserMealsLikes) = mealRepository.registerLike(params)

}