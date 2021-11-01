package com.amalip.exam2.data.api

import com.amalip.exam2.data.dto.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amalip on 10/30/2021.
 */

interface MealApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealResponse>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Call<MealResponse>

    @GET("lookup.php")
    fun getMealDetailById(@Query("i") id: Int): Call<MealResponse>

}