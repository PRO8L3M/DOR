package com.mobile.droidsOnRoids.util

import android.view.View
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import okhttp3.internal.waitMillis

@BindingAdapter("progressActivation")
fun ContentLoadingProgressBar.setProgressActivation(isLoading: Boolean) {
   if (isLoading) show() else hide()
}

@BindingAdapter("app:isActive")
fun View.setViewActivation(isActive: Boolean) {
    isActivated = !isActive
    isClickable = !isActive
}