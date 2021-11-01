package com.amalip.exam2.presentation.login

import com.amalip.exam2.R
import com.amalip.exam2.core.exception.Failure

/**
 * Created by Amalip on 10/29/2021.
 */

sealed class LoginFailure {

    object NotFound : Failure.FeatureFailure(R.string.failure_user_not_found)

}