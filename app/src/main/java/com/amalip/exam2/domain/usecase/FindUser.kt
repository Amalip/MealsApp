package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.data.dto.UserWithLikedMeals
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/29/2021.
 */
class FindUser @Inject constructor(private val userRepository: UserRepository) :
    UseCase<UserWithLikedMeals, User>() {

    override suspend fun run(params: User) = userRepository.findUser(params.name, params.password)

}