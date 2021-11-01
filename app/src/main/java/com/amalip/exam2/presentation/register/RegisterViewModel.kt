package com.amalip.exam2.presentation.register

import androidx.lifecycle.MutableLiveData
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.usecase.RegisterUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUser: RegisterUser) :
    BaseViewModel() {

    val username = MutableLiveData("")
    val password = MutableLiveData("")

    fun doRegister() {
        if (username.value.isNullOrEmpty() || password.value.isNullOrEmpty()) failure.value =
            RegisterFailure.RegisterError
        else
            registerUser(User(name = username.value ?: "", password = password.value ?: "")) {
                it.fold(::handleFailure) {
                    state.value = RegisterViewState.RegistrationSuccess(it.user)

                    true
                }
            }
    }

}