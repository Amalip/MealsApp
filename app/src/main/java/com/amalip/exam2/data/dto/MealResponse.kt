package com.amalip.exam2.data.dto

import com.amalip.exam2.domain.model.Meal
import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 10/30/2021.
 */

@JsonClass(generateAdapter = true)
data class MealResponse(val meals: List<Meal>? = listOf())