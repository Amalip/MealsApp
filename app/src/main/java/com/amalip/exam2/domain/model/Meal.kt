package com.amalip.exam2.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 10/30/2021.
 */


@Entity
@JsonClass(generateAdapter = true)
class Meal(
    @PrimaryKey(autoGenerate = false)
    val idMeal: Int = 0,
    @Json(name = "strMeal") val name: String = "",
    @Json(name = "strMealThumb") val urlThumb: String = "",
    @Json(name = "strInstructions") val description: String = "",
    @Json(name = "strCategory") var category: String = ""
) {
    @Ignore
    var liked: Boolean = false
}