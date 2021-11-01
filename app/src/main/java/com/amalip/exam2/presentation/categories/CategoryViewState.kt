package com.amalip.exam2.presentation.categories

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.Category

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class CategoryViewState : BaseViewState() {

    data class CategoriesReceived(val categories: List<Category>) : BaseViewState()

}