package com.mobile.droidsOnRoids.ui.sudoku

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mobile.droidsOnRoids.R
import com.mobile.droidsOnRoids.common.DOUBLE_BACK_PRESSED_DURATION
import com.mobile.droidsOnRoids.common.UNKNOWN_ERROR
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.SudokuObserver
import com.mobile.droidsOnRoids.databinding.FragmentSudokuBinding
import com.mobile.droidsOnRoids.ext.dataBinding
import com.mobile.droidsOnRoids.ext.snackBar
import com.mobile.droidsOnRoids.ext.toast
import kotlinx.android.synthetic.main.fragment_sudoku.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SudokuFragment : Fragment(R.layout.fragment_sudoku) {

    private val viewModel: SudokuViewModel by viewModel()
    private val dataBinding: FragmentSudokuBinding by dataBinding()
    private var isDoubleClicked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sudoku.observe(
            viewLifecycleOwner,
            SudokuObserver(::onSuccess, ::onFailure, ::onLoading)
        )

        viewModel.isSolved.observe(viewLifecycleOwner, Observer { isSolved ->
            val message = if (isSolved) "It's solved correctly!" else "It's wrong!"
            snackBar(message)
        })

        setupButtonsClickListeners()
        onDoubleBackPressed()
    }

    private fun setupButtonsClickListeners() {
        val numberButtons = listOf(
            sudoku_view_btn_1,
            sudoku_view_btn_2,
            sudoku_view_btn_3,
            sudoku_view_btn_4,
            sudoku_view_btn_5,
            sudoku_view_btn_6,
            sudoku_view_btn_7,
            sudoku_view_btn_8,
            sudoku_view_btn_9
        )
        numberButtons.forEachIndexed { index, btn ->
            btn.setOnClickListener {
                val clickedCell = sudoku_view.getCurrentSelectedCell()
                clickedCell?.let {
                    viewModel.addNewCellValue(clickedCell, index + 1)
                }
            }
        }
        sudoku_view_btn_delete.setOnClickListener {
            sudoku_view.getCurrentSelectedCell()?.let {
                viewModel.deleteCell(it)
            }
        }

        sudoku_view_btn_new.setOnClickListener {
            viewModel.getNewSudoku()
        }

        sudoku_view_btn_check.setOnClickListener {
            viewModel.checkSolution()
        }
    }

    private fun onSuccess(cells: List<Cell>) {
        sudoku_view.fillCells(cells)
        dataBinding.isLoading = false
    }

    private fun onFailure(exception: Exception) {
        snackBar(exception.localizedMessage ?: UNKNOWN_ERROR)
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