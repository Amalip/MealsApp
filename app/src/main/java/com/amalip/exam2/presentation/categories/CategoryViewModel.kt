package com.amalip.exam2.presentation.categories

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.usecase.GetCategories
import com.amalip.exam2.domain.usecase.SaveCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val saveCategories: SaveCategories
) : BaseViewModel() {

    fun getCategories() {
        getCategories(UseCase.None()) {
            it.fold(::handleFailure) { response ->
                state.value = CategoryViewState.CategoriesReceived(response.categories ?: listOf())

                saveCategories(response.categories ?: listOf())

                true
            }
        }
    }

    private fun saveCategories(cocktails: List<Category>) {
        saveCategories(cocktails) {
            it.fold(::handleFailure) {

            }
        }
    }

}