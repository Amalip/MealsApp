package com.amalip.exam2.data.source

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.functional.Either
import com.amalip.exam2.core.plataform.AuthManager
import com.amalip.exam2.data.dao.UserDao
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.repository.UserRepository
import com.amalip.exam2.framework.api.ApiRequest
import com.amalip.exam2.presentation.login.LoginFailure
import com.amalip.exam2.presentation.register.RegisterFailure
import javax.inject.Inject

/**
 * Created by Amalip on 10/29/2021.
 */
class UserRepositoryImpl @Inject constructor(
    private val authManager: AuthManager,
    private val userDao: UserDao
) : UserRepository,
    ApiRequest {

    override fun getLocalUser(): Either<Failure, UserWithLikedMeals> {
        val result = authManager.user

        return result?.let {
            Either.Right(it)
        } ?: Either.Left(Failure.Unauthorized)
    }

    override fun doLogout(): Either<Failure, Boolean> {
        authManager.user = null
        return Either.Right(true)
    }

    override fun findUser(username: String, password: String): Either<Failure, UserWithLikedMeals> {
        val result = userDao.findUser(username, password)

        return result?.let {
            authManager.user = it
            Either.Right(it)
        } ?: Either.Left(LoginFailure.NotFound)
    }

    override fun registerUser(user: User): Either<Failure, UserWithLikedMeals> {
        val userExists = userDao.findUserByUsername(user.name)

        return if (userExists == null) {
            user.setRandomImage()
            val result = userDao.registerUser(user)

            result?.let {
                if (it > 0)
                    findUser(user.name, user.password)
                else
                    Either.Left(RegisterFailure.RegisterError)
            } ?: Either.Left(RegisterFailure.RegisterError)
        } else Either.Left(RegisterFailure.UserExists)
    }

}