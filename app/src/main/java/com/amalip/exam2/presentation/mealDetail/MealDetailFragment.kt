package com.amalip.exam2.presentation.mealDetail

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.databinding.MealDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class MealDetailFragment : BaseFragment(R.layout.meal_detail_fragment) {

    private lateinit var binding: MealDetailFragmentBinding

    private val mealDetailViewModel by viewModels<MealDetailViewModel>()
    private val args by navArgs<MealDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealDetailViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)

            if (args.mealId == 0) getRandomMeal()
            else getMealById(args.mealId)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is MealDetailViewState.MealReceived -> binding.apply {
                clDetail.isVisible = true
                emptyView.isGone = true
                meal = state.meal
            }
        }
    }

    override fun setBinding(view: View) {
        binding = MealDetailFragmentBinding.bind(view)

        binding.apply {
            lifecycleOwner = this@MealDetailFragment

            txvDescription.movementMethod = ScrollingMovementMethod()
        }

        baseActivity.setBottomNavVisibility(
            if (args.mealId != 0) View.GONE
            else View.VISIBLE
        )
    }

}