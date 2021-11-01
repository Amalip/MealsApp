package com.amalip.exam2.presentation.meals

import androidx.lifecycle.MutableLiveData
import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.usecase.ExecuteLike
import com.amalip.exam2.domain.usecase.GetLikedMealsByUser
import com.amalip.exam2.domain.usecase.GetLocalUser
import com.amalip.exam2.domain.usecase.GetMealsByCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val getLikedMealsByUser: GetLikedMealsByUser,
    private val getMealsByCategory: GetMealsByCategory,
    private val executeLike: ExecuteLike
) : BaseViewModel() {

    private val list = MutableLiveData(mutableListOf<Meal>())
    private var filter = ""
    private var likedMealsIds = MutableLiveData(mutableListOf<Int>())
    private lateinit var userWithLikedMeals: UserWithLikedMeals

    init {
        //Getting user info
        getLocalUser(UseCase.None()) {
            it.fold({}) {
                userWithLikedMeals = it

                //Getting liked meals
                getLikedMealsByUser(it.user.userId) {
                    it.fold(::handleFailure) {
                        likedMealsIds.value = it.map { it.idMeal }.toMutableList()

                        true
                    }
                }

                true
            }
        }
    }

    fun getMealsByCategory(category: String) {
        getMealsByCategory(category) {
            it.fold(::handleFailure) {
                it.onEach { it.liked = likedMealsIds.value?.contains(it.idMeal) == true }
                list.value = it.toMutableList()
                state.value = MealsViewState.MealsReceived(it)
                true
            }
        }
    }

    fun filterByName(name: String) {
        filter = name
        state.value =
            MealsViewState.MealsReceived(list.value?.filter { it.name.contains(name) }
                ?: emptyList())
    }

    fun executeLike(userMealsLikes: UserMealsLikes) {
        if (::userWithLikedMeals.isInitialized) {
            executeLike(userMealsLikes.apply { userId = userWithLikedMeals.user.userId }) {
                it.fold(::handleFailure) {
                    list.value?.find { it.idMeal == userMealsLikes.idMeal }?.liked =
                        userMealsLikes.liked
                    if (filter.isNotEmpty()) filterByName(filter)
                    else state.value =
                        MealsViewState.MealChanged(list.value?.find { it.idMeal == userMealsLikes.idMeal }!!)
                }
            }
        } else failure.value = Failure.Unauthorized
    }

}