package com.amalip.exam2.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.databinding.RegisterFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class RegisterFragment : BaseFragment(R.layout.register_fragment) {

    private lateinit var binding: RegisterFragmentBinding

    private val accountViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }

    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is RegisterViewState.RegistrationSuccess ->
                navController.popBackStack(R.id.loginFragment, true)
        }
    }

    override fun setBinding(view: View) {
        binding = RegisterFragmentBinding.bind(view)

        binding.apply {
            lifecycleOwner = this@RegisterFragment

            vm = accountViewModel

            btnDoRegister.setOnClickListener { accountViewModel.doRegister() }
            txvGoToRegister.setOnClickListener { navController.popBackStack() }

        }

    }

}