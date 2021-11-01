package com.amalip.exam2.framework.api

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.core.plataform.NetworkHandler
import retrofit2.Call

/**
 * Created by Amalip on 9/28/2021.
 */

interface ApiRequest {

    fun <T, R> makeRequest(
        networkHandler: NetworkHandler,
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    val response = call.execute()
                    when (response.isSuccessful) {
                        true -> Either.Right(transform(response.body() ?: default))
                        false -> Either.Left(Failure.ServerError(500, ""))
                    }
                } catch (e: Exception) {
                    Either.Left(Failure.ServerError(500, e.message))
                }
            }
            false -> Either.Left(Failure.NetworkConnection)
        }
    }

}