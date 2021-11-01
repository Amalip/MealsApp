package com.amalip.exam2.domain.repository

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.User

/**
 * Created by Amalip on 10/29/2021.
 */

interface UserRepository {

    fun getLocalUser(): Either<Failure, UserWithLikedMeals>

    fun doLogout(): Either<Failure, Boolean>

    fun findUser(username: String, password: String): Either<Failure, UserWithLikedMeals>

    fun registerUser(user: User): Either<Failure, UserWithLikedMeals>

}