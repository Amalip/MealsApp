package com.amalip.exam2.presentation.account

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.exam2.R
import com.amalip.exam2.core.extension.failure
import com.amalip.exam2.core.extension.observe
import com.amalip.exam2.core.presentation.BaseFragment
import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.databinding.AccountFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class AccountFragment : BaseFragment(R.layout.account_fragment) {

    private lateinit var binding: AccountFragmentBinding

    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }

    }

    override fun onResume() {
        super.onResume()

        accountViewModel.getLocalUser()
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is AccountViewState.LoggedUser -> binding.user = state.user
            is AccountViewState.UserNotFound -> navController.navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment())
        }
    }

    override fun setBinding(view: View) {
        binding = AccountFragmentBinding.bind(view)

        binding.apply {
            lifecycleOwner = this@AccountFragment
            btnLogout.setOnClickListener { accountViewModel.doLogout() }
        }

    }

}