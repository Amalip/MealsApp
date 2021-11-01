package com.amalip.exam2.data.source

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.core.functional.getOrElse
import com.amalip.exam2.core.functional.getOrThrow
import com.amalip.exam2.core.functional.leftOrElse
import com.amalip.exam2.core.plataform.NetworkHandler
import com.amalip.exam2.data.api.MealApi
import com.amalip.exam2.data.dao.MealDao
import com.amalip.exam2.data.dao.UserMealLikesDao
import com.amalip.exam2.data.dto.MealResponse
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.repository.MealRepository
import com.amalip.exam2.framework.api.ApiRequest
import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by Amalip on 10/30/2021.
 */

class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao,
    private val userMealLikesDao: UserMealLikesDao,
    private val networkHandler: NetworkHandler
) : MealRepository, ApiRequest {

    override fun getRandomMeal(): Either<Failure, Meal> {
        val result = makeRequest(
            networkHandler, mealApi.getRandomMeal(), { it.meals?.get(0) ?: Meal() }, MealResponse(
                emptyList()
            )
        )

        return if (result.isLeft) {
            val localResult = mealDao.getMeals("%%")
            val localRandomMeal = if (localResult.isNotEmpty()) localResult[Random.nextInt(
                0,
                localResult.size - 1
            )] else Meal(0)

            if (localRandomMeal.idMeal > 0) Either.Right(localRandomMeal)
            else result
        } else {
            saveMeal(listOf(result.getOrThrow()))

            result
        }
    }

    override fun getMealsByCategory(category: String): Either<Failure, List<Meal>> {
        val result = makeRequest(
            networkHandler, mealApi.getMealsByCategory(category), {
                it.meals?.onEach { meal -> meal.category = category }
            }, MealResponse(
                emptyList()
            )
        )

        return if (result.isLeft) {
            val localResult = mealDao.getMealsByCategory("%$category%")

            if (localResult.isNullOrEmpty()) Either.Left(result.leftOrElse(Failure.NetworkConnection))
            else Either.Right(localResult)
        } else {
            val meals = result.getOrElse(emptyList()) ?: emptyList()
            saveMeal(meals)

            Either.Right(meals)
        }
    }

    override fun getMealDetailById(id: Int): Either<Failure, Meal> {
        val result = makeRequest(
            networkHandler,
            mealApi.getMealDetailById(id),
            { it.meals?.get(0) ?: Meal() },
            MealResponse(
                emptyList()
            )
        )

        return if (result.isLeft) {
            val localResult = mealDao.getMealById(id)

            if (localResult.idMeal > 0) Either.Right(localResult)
            else result
        } else {
            saveMeal(listOf(result.getOrThrow()))

            result
        }
    }

    override fun saveMeal(meal: List<Meal>): Either<Failure, List<Long>> {
        val result = mealDao.saveMeals(meal)

        return if (result.size == meal.size) Either.Right(result)
        else Either.Left(Failure.DatabaseError)
    }

    override fun registerLike(userMealsLikes: UserMealsLikes): Either<Failure, Long> {
        val result = userMealLikesDao.executeLikes(userMealsLikes)

        return if (result > 0) Either.Right(result)
        else Either.Left(Failure.DatabaseError)
    }

    override fun getLikedMealsByUserId(userId: Int): Either<Failure, List<UserMealsLikes>> {
        val result = userMealLikesDao.getLikedMealsByUserId(userId)

        return if (result.isEmpty()) Either.Left(Failure.SilenceFail)
        else Either.Right(result)
    }

}