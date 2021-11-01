package com.amalip.exam2.data.api

import com.amalip.exam2.data.dto.CategoriesResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Amalip on 10/26/2021.
 */

interface CategoryApi {

    @GET("categories.php")
    fun getCategories(): Call<CategoriesResponse>

}