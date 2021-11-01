package com.amalip.exam2.data.dto

import androidx.room.Entity
import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 10/30/2021.
 */

@JsonClass(generateAdapter = true)
@Entity(primaryKeys = ["userId", "idMeal"])
data class UserMealsLikes(

    var userId: Int = 0,
    val idMeal: Int = 0,
    val liked: Boolean = false

)