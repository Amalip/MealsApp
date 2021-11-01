package com.amalip.exam2.data.dto

import com.amalip.exam2.domain.model.Category
import com.squareup.moshi.JsonClass

/**
 * Created by Amalip on 10/26/2021.
 */

@JsonClass(generateAdapter = true)
data class CategoriesResponse(val categories: List<Category>? = listOf())
