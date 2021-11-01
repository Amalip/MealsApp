package com.amalip.exam2.core.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import com.amalip.exam2.core.exception.Failure

/**
 * Created by Amalip on 9/27/2021.
 */

abstract class BaseActivity : AppCompatActivity(), OnFailure {

    abstract fun layoutId(): Int
    abstract val fragmentContainer: FragmentContainerView

    internal fun hideKeyBoard() {
        val view = this.findViewById<View>(android.R.id.content)
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    internal fun showKeyBoard() {
        val view = this.findViewById<View>(android.R.id.content)
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInputFromWindow(view.windowToken, 0, 0)
        }
    }

    abstract fun setUpNavigation(navController: NavController)

    override fun handleFailure(failure: Failure?) {
        showProgress(false)
    }

    abstract fun showProgress(show: Boolean)

    abstract fun setBottomNavVisibility(visibility: Int)

    abstract fun setBinding()

}