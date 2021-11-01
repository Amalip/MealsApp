package com.amalip.exam2.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.databinding.LoginFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }

    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is LoginViewState.UserFound ->
                navController.popBackStack()
        }
    }

    override fun setBinding(view: View) {
        binding = LoginFragmentBinding.bind(view)

        binding.apply {
            lifecycleOwner = this@LoginFragment

            vm = loginViewModel

            txvGoToRegister.setOnClickListener { navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment()) }

            btnDoLogin.setOnClickListener { loginViewModel.doLogin() }

        }

    }

}