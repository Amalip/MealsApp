package com.amalip.exam2.domain.usecase

import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Created by Amalip on 10/6/2021.
 */


class DoLogout @Inject constructor(private val userRepository: UserRepository) :
    UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = userRepository.doLogout()

}