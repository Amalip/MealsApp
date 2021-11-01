package com.amalip.exam2.presentation.register

import com.amalip.exam2.R
import com.amalip.exam2.core.exception.Failure

/**
 * Created by Amalip on 10/29/2021.
 */

sealed class RegisterFailure {

    object UserExists : Failure.FeatureFailure(R.string.failure_user_exists)
    object RegisterError : Failure.FeatureFailure(R.string.failure_register_error)

}