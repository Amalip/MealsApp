package com.amalip.exam2.presentation.login

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class LoginViewState : BaseViewState() {

    data class UserFound(val user: User) : BaseViewState()

}