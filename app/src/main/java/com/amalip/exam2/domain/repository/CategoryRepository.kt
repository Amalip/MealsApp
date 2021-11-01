package com.amalip.exam2.domain.repository

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.data.dto.CategoriesResponse
import com.amalip.exam2.domain.model.Category

/**
 * Created by Amalip on 10/26/2021.
 */
interface CategoryRepository {

    fun getCategories(): Either<Failure, CategoriesResponse>

    fun saveCategories(categories: List<Category>): Either<Failure, Boolean>

}