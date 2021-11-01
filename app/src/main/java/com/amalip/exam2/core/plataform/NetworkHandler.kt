package com.amalip.exam2.core.plataform

import android.content.Context
import com.amalip.exam2.core.extension.networkInfo
import javax.inject.Inject

/**
 * Created by Amalip on 9/28/2021.
 */

class NetworkHandler @Inject constructor(private val context: Context) {

    val isConnected get() = context.networkInfo?.isConnectedOrConnecting == true

}