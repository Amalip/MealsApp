package com.amalip.exam2.presentation.register

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.Category
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 9/27/2021.
 */
sealed class RegisterViewState : BaseViewState() {

    data class RegistrationSuccess(val user: User) : BaseViewState()

}