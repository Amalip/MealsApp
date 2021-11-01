package com.amalip.exam2.domain.repository

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.domain.model.Meal

/**
 * Created by Amalip on 10/30/2021.
 */
interface MealRepository {

    fun getRandomMeal(): Either<Failure, Meal>

    fun getMealsByCategory(category: String): Either<Failure, List<Meal>>

    fun getMealDetailById(id: Int): Either<Failure, Meal>

    fun saveMeal(meal: List<Meal>): Either<Failure, List<Long>>

    fun registerLike(userMealsLikes: UserMealsLikes): Either<Failure, Long>

    fun getLikedMealsByUserId(userId: Int): Either<Failure, List<UserMealsLikes>>

}