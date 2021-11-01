package com.amalip.exam2.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.amalip.exam2.R

/**
 * Created by Amalip on 9/29/2021.
 */

@BindingAdapter("loadFromURLCircular")
fun ImageView.loadFromURLCircular(url: String?) = url?.let {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_taco)
        error(R.drawable.ic_taco)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("loadFromURL")
fun ImageView.loadFromURL(url: String?) = url?.let {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_taco)
        error(R.drawable.ic_taco)
    }
}