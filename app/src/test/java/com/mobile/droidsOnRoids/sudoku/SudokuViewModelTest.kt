package com.mobile.droidsOnRoids.sudoku

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mobile.droidsOnRoids.TestCoroutineRule
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
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

class SudokuViewModelTest {
    @ExperimentalCoroutinesApi
    @RunWith(MockitoJUnitRunner::class)
    class SplashScreenViewModelTest : KoinTest {

        @get:Rule
        val testInstantTaskExampleUnitTest: TestRule = InstantTaskExecutorRule()

        @get:Rule
        val testCoroutineRule = TestCoroutineRule()

        @Mock
        private lateinit var sudokuObserver: Observer<Result<List<Cell>>>

        @Mock
        private lateinit var repo: SudokuRepository

        private val cells = listOf(Cell(row = 1, column = 1, value = 2, isSolution = true, isEditable = true))

        @Test
        fun `should return mocked cell list`() {
            testCoroutineRule.runBlockingTest {
                doReturn(Result.Success(cells)).`when`(repo).getSudoku()
                val viewModel = SudokuViewModel(repo)
                viewModel.sudoku.observeForever(sudokuObserver)
                verify(repo).getSudoku()
                verify(sudokuObserver).onChanged(Result.Success(cells))
                viewModel.sudoku.removeObserver(sudokuObserver)
            }
        }
    }
}