package com.mobile.droidsOnRoids.start

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.droidsOnRoids.TestCoroutineRule
import com.mobile.droidsOnRoids.repository.SudokuRepository
import com.mobile.droidsOnRoids.ui.sudoku.SudokuViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

class StartViewModelTest {
   /* @ExperimentalCoroutinesApi
    @RunWith(MockitoJUnitRunner::class)
    class SplashScreenViewModelTest : KoinTest {

        @get:Rule
        val testInstantTaskExampleUnitTest: TestRule = InstantTaskExecutorRule()

        @get:Rule
        val testCoroutineRule = TestCoroutineRule()

        @Mock
        private lateinit var itemsObserver: Observer<List<Item>>

        @Mock
        private lateinit var repo: SudokuRepository

        @Test
        fun `should return mocked item list`() {
            testCoroutineRule.runBlockingTest {
                doReturn(emptyList<Item>()).`when`(repo).getMockedItems()
                val viewModel = SudokuViewModel(repo)
                viewModel.items.observeForever(itemsObserver)
                verify(repo).getMockedItems()
                verify(itemsObserver).onChanged(emptyList())
                viewModel.items.removeObserver(itemsObserver)
            }
        }
    }*/
}