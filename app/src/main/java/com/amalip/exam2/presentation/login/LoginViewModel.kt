package com.amalip.exam2.presentation.login

import androidx.lifecycle.MutableLiveData
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.usecase.FindUser
import com.amalip.exam2.presentation.register.RegisterViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LoginViewModel @Inject constructor(private val findUser: FindUser) : BaseViewModel() {

    val username = MutableLiveData("")
    val password = MutableLiveData("")

    fun doLogin() {
        findUser(User(name = username.value ?: "", password = password.value ?: "")) {
            it.fold(::handleFailure) {
                state.value = LoginViewState.UserFound(it.user)

                true
            }
        }

    }

}