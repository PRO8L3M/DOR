package com.mobile.droidsOnRoids.splashScreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.droidsOnRoids.TestCoroutineRule
import com.mobile.droidsOnRoids.common.SPLASH_SCREEN_DURATION
import com.mobile.droidsOnRoids.ui.splashScreen.SplashScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashScreenViewModelTest : KoinTest {

    @get:Rule
    val testInstantTaskExampleUnitTest: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var isSplashScreenFinishedObserver: Observer<Boolean>

    private val viewModel = SplashScreenViewModel()

    @Test
    fun `should return true when splash screen finished`() {
        testCoroutineRule.runBlockingTest {
            viewModel.isSplashScreenFinished.observeForever(isSplashScreenFinishedObserver)
            viewModel.runSplashScreen()
            // todo change delay somehow
            delay(SPLASH_SCREEN_DURATION)
            assert(viewModel.isSplashScreenFinished.value == true)
        }
    }
}