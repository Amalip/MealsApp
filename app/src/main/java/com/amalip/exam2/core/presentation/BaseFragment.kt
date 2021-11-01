package com.amalip.exam2.core.presentation

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.amalip.exam2.R
import com.amalip.exam2.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), OnFailure {

    val navController by lazy { findNavController() }
    val baseActivity by lazy { requireActivity() as BaseActivity }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.setUpNavigation(navController)

        baseActivity.window.statusBarColor = resources.getColor(statusBarColor())
        baseActivity.window.setBackgroundDrawable(windowBackgroundDrawable())

        setBinding(view)
    }

    abstract fun setBinding(view: View)

    open fun onViewStateChanged(state: BaseViewState?) {
        when (state) {
            BaseViewState.ShowLoading -> showLoader(true)
            BaseViewState.HideLoading -> showLoader(false)
            else -> {
            }
        }
    }

    internal fun hideKeyboard() {
        (activity as? BaseActivity)?.hideKeyBoard()
    }

    internal fun showKeyboard() {
        (activity as? BaseActivity)?.showKeyBoard()
    }

    open fun showLoader(show: Boolean) = baseActivity.showProgress(show)

    open fun statusBarColor() = R.color.white
    open fun windowBackgroundDrawable(): Drawable? =
        ColorDrawable(resources.getColor(R.color.white))

    inline fun <reified T : ViewModel> Fragment.hiltNavGraphViewModels(@IdRes navGraphIdRes: Int) =
        viewModels<T>(
            ownerProducer = { findNavController().getBackStackEntry(navGraphIdRes) },
            factoryProducer = { defaultViewModelProviderFactory }
        )

    private fun showToast(message: String) = Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()

    override fun handleFailure(failure: Failure?) {
        showLoader(false)
        when (failure) {
            is Failure.Unauthorized -> {
                failure.defaultMessage?.let { showToast(getString(it)) }
            }
            is Failure.ServerError -> showToast(failure.serverMessage ?: "")
            is Failure.DatabaseError -> {
                failure.defaultMessage?.let { showToast(getString(it)) }
            }
            is Failure.NetworkConnection -> {
                failure.defaultMessage?.let { showToast(getString(it)) }
            } //showToast(getString(R.string.error_no_internet_connection))
            is Failure.FeatureFailure -> failure.defaultMessage?.let { showToast(getString(it)) }
        }
    }

}