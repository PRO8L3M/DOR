package com.mobile.droidsOnRoids.ui.splashScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.ext.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isSplashScreenFinished.observe(viewLifecycleOwner, Observer(::observeSplashScreenLiveData))
        viewModel.runSplashScreen()
    }

    private fun observeSplashScreenLiveData(isSplashScreenFinished: Boolean) {
        if (isSplashScreenFinished) navigateTo(R.id.action_splashScreenFragment_to_startFragment)
    }
}
