package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.repository.CategoryRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/26/2021.
 */
class SaveCategories @Inject constructor(private val categoryRepository: CategoryRepository) :
    UseCase<Boolean, List<Category>>() {

    override suspend fun run(params: List<Category>) = categoryRepository.saveCategories(params)

}