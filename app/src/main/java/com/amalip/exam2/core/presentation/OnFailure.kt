package com.amalip.exam2.core.presentation

import com.amalip.exam2.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

interface OnFailure {

    fun handleFailure(failure: Failure?)

}