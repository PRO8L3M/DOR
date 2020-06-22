package com.mobile.droidsOnRoids.ui.sudoku

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.common.DOUBLE_BACK_PRESSED_DURATION
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.SudokuObserver
import com.mobile.droidsOnRoids.data.network.doOnFailure
import com.mobile.droidsOnRoids.data.network.error.SudokuError
import com.mobile.droidsOnRoids.databinding.FragmentSudokuBinding
import com.mobile.droidsOnRoids.ext.dataBinding
import com.mobile.droidsOnRoids.ext.snackBar
import com.mobile.droidsOnRoids.ext.toast
import kotlinx.android.synthetic.main.fragment_sudoku.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SudokuFragment : Fragment(R.layout.fragment_sudoku) {

    private val viewModel: SudokuViewModel by viewModel()
    private val dataBinding: FragmentSudokuBinding by dataBinding()
    private var isDoubleClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeSudokuFetchLiveData()
        observeSudokuLiveData()
        observeSolutionLiveData()
        setupButtonsClickListeners()
        onDoubleBackPressed()
    }

    private fun observeSudokuFetchLiveData() {
        viewModel.isSudokuFetched.observe(viewLifecycleOwner, Observer { isFetched ->
           isFetched.doOnFailure { exception ->
               when(exception) {
                   is SudokuError.NetworkError -> snackBar(getString(R.string.sudoku_fetched_error))
               }
           }
        })
    }

    private fun observeSolutionLiveData() {
        viewModel.isSolved.observe(viewLifecycleOwner, Observer { isSolved ->
            val message =
                if (isSolved) getString(R.string.sudoku_solved_message) else getString(R.string.sudoku_unsolved_message)
            snackBar(message)
        })
    }

    private fun observeSudokuLiveData() {
        viewModel.sudoku.observe(
            viewLifecycleOwner,
            SudokuObserver(::onSuccess, ::onFailure, ::onLoading)
        )
    }

    private fun setupButtonsClickListeners() = with(dataBinding) {
        val numberButtons = listOf(
            sudokuViewBtn1,
            sudokuViewBtn2,
            sudokuViewBtn3,
            sudokuViewBtn4,
            sudokuViewBtn5,
            sudokuViewBtn6,
            sudokuViewBtn7,
            sudokuViewBtn8,
            sudokuViewBtn9
        )

        numberButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                val clickedCell = sudokuView.getCurrentSelectedCell()
                clickedCell?.let {
                    viewModel.addNewCellValue(clickedCell, index + 1)
                }
            }
        }

        sudokuViewBtnDelete.setOnClickListener {
            sudoku_view.getCurrentSelectedCell()?.let {
                viewModel.deleteCell(it)
            }
        }

        sudokuViewBtnNew.setOnClickListener {
            viewModel.getNewSudoku()
        }

        sudokuViewBtnCheck.setOnClickListener {
            viewModel.checkSolution()
        }
    }

    private fun onSuccess(cells: List<Cell>) = with(dataBinding) {
        sudokuView.fillCells(cells)
        isLoading = false
    }

    private fun onFailure(exception: Exception) {
        when(exception) {
            is SudokuError.DatabaseError -> snackBar(getString(R.string.sudoku_save_to_db_error))
        }
        dataBinding.isLoading = false
    }

    private fun onLoading() {
        dataBinding.isLoading = true
    }

    private fun onDoubleBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (isDoubleClicked) {
                requireActivity().finish()
                return@addCallback
            }
            isDoubleClicked = true
            toast(resources.getString(R.string.tap_again_to_exit))
            lifecycleScope.launch {
                delay(DOUBLE_BACK_PRESSED_DURATION)
                isDoubleClicked = false
            }
        }
    }
}