package com.amalip.exam2.data.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.amalip.exam2.domain.model.Meal
import com.amalip.exam2.domain.model.User
import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 10/30/2021.
 */

@JsonClass(generateAdapter = true)
data class UserWithLikedMeals(

    @Embedded val user: User,
    @Relation(
        parentColumn = "userId", entityColumn = "idMeal", associateBy = Junction(
            UserMealsLikes::class
        )
    )
    val meals: List<Meal>

)
