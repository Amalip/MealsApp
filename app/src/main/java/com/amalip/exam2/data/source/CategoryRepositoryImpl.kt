package com.amalip.exam2.data.source

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.core.plataform.NetworkHandler
import com.amalip.exam2.data.api.CategoryApi
import com.amalip.exam2.data.dao.CategoryDao
import com.amalip.exam2.data.dto.CategoriesResponse
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.repository.CategoryRepository
import com.amalip.exam2.framework.api.ApiRequest
import javax.inject.Inject

/**
 * Created by Amalip on 10/26/2021.
 */

class CategoryRepositoryImpl @Inject constructor(
    private val categoryApi: CategoryApi,
    private val categoryDao: CategoryDao,
    private val networkHandler: NetworkHandler
) : CategoryRepository, ApiRequest {

    override fun getCategories(): Either<Failure, CategoriesResponse> {
        val result = makeRequest(
            networkHandler, categoryApi.getCategories(), { it }, CategoriesResponse(
                emptyList()
            )
        )

        return if (result.isLeft) {
            val localResult = categoryDao.getCategories()

            if (localResult.isEmpty()) result
            else Either.Right(CategoriesResponse(localResult))

        } else result
    }

    override fun saveCategories(categories: List<Category>): Either<Failure, Boolean> {
        val result = categoryDao.saveCategories(categories)
        return if (result.size == categories.size)
            Either.Right(true)
        else Either.Left(Failure.DatabaseError)
    }


}