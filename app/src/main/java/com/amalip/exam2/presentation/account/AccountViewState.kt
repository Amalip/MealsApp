package com.amalip.exam2.presentation.account

import com.amalip.exam2.core.presentation.BaseViewState
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 10/6/2021.
 */

abstract class AccountViewState : BaseViewState() {

    data class LoggedUser(val user: User) : BaseViewState()

    object UserNotFound : BaseViewState()

}