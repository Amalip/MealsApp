package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.data.dto.CategoriesResponse
import com.amalip.exam2.domain.repository.CategoryRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/26/2021.
 */
class GetCategories @Inject constructor(private val categoryRepository: CategoryRepository) :
    UseCase<CategoriesResponse, UseCase.None>() {

    override suspend fun run(params: None) = categoryRepository.getCategories()

}