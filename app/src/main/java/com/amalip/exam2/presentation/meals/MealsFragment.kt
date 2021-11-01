package com.amalip.exam2.presentation.meals

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.data.dto.UserMealsLikes
import com.amalip.exam2.databinding.MealsFragmentBinding
import com.amalip.exam2.domain.model.Meal
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class MealsFragment : BaseFragment(R.layout.meals_fragment) {

    private lateinit var binding: MealsFragmentBinding

    private val adapter: MealsAdapter by lazy { MealsAdapter() }
    private val mealsViewModel by viewModels<MealsViewModel>()
    private val args by navArgs<MealsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealsViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            getMealsByCategory(args.category)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealsViewState.MealsReceived -> setUpAdapter(state.meals)
            is MealsViewState.MealChanged -> adapter.setLike(state.meal)
        }
    }

    private fun setUpAdapter(meals: List<Meal>) {
        binding.emptyView.isVisible = meals.isEmpty()

        adapter.addData(meals)

        adapter.setListener { meal, executeLike ->
            if (executeLike) {
                mealsViewModel.executeLike(UserMealsLikes(idMeal = meal.idMeal, liked = !meal.liked))
            } else
                navController.navigate(
                    MealsFragmentDirections.actionMealsFragmentToMealDetailFragment(
                        meal.idMeal
                    )
                )
        }

        binding.rcMeals.apply {
            isVisible = meals.isNotEmpty()
            adapter = this@MealsFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = MealsFragmentBinding.bind(view)

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this

        binding.apply {
            swpRefresh.apply {
                setOnRefreshListener {
                    mealsViewModel.getMealsByCategory(args.category)
                    isRefreshing = false
                }
            }

            svMeals.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    mealsViewModel.filterByName(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    mealsViewModel.filterByName(newText ?: "")
                    return true
                }

            })
        }

        baseActivity.setBottomNavVisibility(View.GONE)
    }


}