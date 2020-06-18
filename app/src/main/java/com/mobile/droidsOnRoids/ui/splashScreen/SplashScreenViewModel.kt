package com.mobile.droidsOnRoids.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.droidsOnRoids.common.SPLASH_SCREEN_DURATION
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _isSplashScreenFinished: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isSplashScreenFinished: LiveData<Boolean> = _isSplashScreenFinished

    fun runSplashScreen(duration: Long = SPLASH_SCREEN_DURATION) {
        viewModelScope.launch {
            delay(duration)
            _isSplashScreenFinished.postValue(true)
        }
    }
}