package com.amalip.exam2.presentation.categories

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.databinding.CategoryFragmentBinding
import com.amalip.exam2.domain.model.Category
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CategoryFragment : BaseFragment(R.layout.category_fragment) {

    private lateinit var binding: CategoryFragmentBinding

    private val adapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private val categoriesViewModel by viewModels<CategoryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onResume() {
        super.onResume()
        categoriesViewModel.getCategories()
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CategoryViewState.CategoriesReceived -> setUpAdapter(state.categories)
        }
    }

    private fun setUpAdapter(categories: List<Category>) {
        binding.emptyView.isVisible = categories.isEmpty()

        adapter.addData(categories)

        adapter.setListener {
            navController.navigate(
                CategoryFragmentDirections.actionCategoryFragmentToMealsFragment(
                    it.name
                )
            )
        }

        binding.rcCategories.apply {
            isVisible = categories.isNotEmpty()
            adapter = this@CategoryFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = CategoryFragmentBinding.bind(view)

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this

        binding.apply {
            swpRefresh.apply {
                setOnRefreshListener {

                    isRefreshing = false
                }
            }
        }

        baseActivity.setBottomNavVisibility(View.VISIBLE)
    }


}