package com.amalip.exam2.presentation.account

import com.amalip.exam2.core.exception.Failure
import com.amalip.exam2.core.interactor.UseCase
import com.amalip.exam2.core.presentation.BaseViewModel
import com.amalip.exam2.domain.model.User
import com.amalip.exam2.domain.usecase.DoLogout
import com.amalip.exam2.domain.usecase.GetLikedMealsByUser
import com.amalip.exam2.domain.usecase.GetLocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getLocalUser: GetLocalUser,
    private val doLogout: DoLogout,
    private val getLikedMealsByUser: GetLikedMealsByUser,
) :
    BaseViewModel() {

    fun getLocalUser() {
        getLocalUser(UseCase.None()) {
            it.fold(::userNotFound) { localUser ->
                getLikedMealsByUser(localUser.user.userId) {
                    it.fold({
                        setUserInfo(localUser.user)
                        ::handleFailure
                    }) {
                        setUserInfo(localUser.user.apply { likes = it.size })
                        true
                    }
                }
            }
        }
    }

    private fun setUserInfo(user: User) {
        state.value = AccountViewState.LoggedUser(user)
    }

    fun doLogout() {
        doLogout(UseCase.None()) {
            it.fold(::handleFailure) {
                if (it) state.value = AccountViewState.UserNotFound
            }
        }
    }

    private fun userNotFound(failure: Failure) {
        state.value = AccountViewState.UserNotFound
        handleFailure(failure)
    }

}