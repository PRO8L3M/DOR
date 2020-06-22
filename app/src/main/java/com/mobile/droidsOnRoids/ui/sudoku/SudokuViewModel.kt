package com.mobile.droidsOnRoids.ui.sudoku

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
import com.mobile.droidsOnRoids.data.network.doOnFailure
import com.mobile.droidsOnRoids.data.network.doOnSuccess
import com.mobile.droidsOnRoids.ext.convertToSudokuChain
import com.mobile.droidsOnRoids.repository.ISudokuRepository
import com.mobile.droidsOnRoids.util.SingleLiveEvent
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: ISudokuRepository) : ViewModel() {

    private val _sudoku: MutableLiveData<Result<List<Cell>>> by lazy { MutableLiveData<Result<List<Cell>>>() }
    val sudoku: MutableLiveData<Result<List<Cell>>> = _sudoku

    private val _isSolved: SingleLiveEvent<Boolean> by lazy { SingleLiveEvent<Boolean>() }
    val isSolved: SingleLiveEvent<Boolean> = _isSolved

    private val _isSudokuFetched: SingleLiveEvent<Result<Boolean>> by lazy { SingleLiveEvent<Result<Boolean>>() }
    val isSudokuFetched: SingleLiveEvent<Result<Boolean>> = _isSudokuFetched

    init {
        initGetSudoku()
    }

    private fun initGetSudoku() {
        viewModelScope.launch {
            repository.getSudoku()
                .doOnSuccess { _sudoku.value = Result.Success(it) }
                .doOnFailure { getSudokuRemotely() }
        }
    }

    private fun getSudokuRemotely() {
        viewModelScope.launch {
            _sudoku.value = Result.Loading
            repository.fetchSudoku()
                .doOnFailure {
                    _isSudokuFetched.value = Result.Failure(it)
                }
            getSudokuLocally()
        }
    }

    private fun getSudokuLocally() {
        viewModelScope.launch {
            _sudoku.value = Result.Loading
            val result = repository.getSudoku()
            _sudoku.value = result
        }
    }


    private fun updateCell(cell: Cell) {
        if (!cell.isEditable) return
        viewModelScope.launch {
            repository.updateCell(cell)
            getSudokuLocally()
        }
    }

    fun checkSolution() {
        viewModelScope.launch {
            repository.getSudokuSolution().doOnSuccess { solution ->
                _sudoku.value?.doOnSuccess { sudoku ->
                    val solutionChain = solution.convertToSudokuChain()
                    val sudokuChain = sudoku.convertToSudokuChain()
                    val isTheSame = solutionChain == sudokuChain
                    _isSolved.value = isTheSame
                }
            }
        }
    }

    fun deleteCell(cell: Cell) {
        val newCell = cell.copy(value = 0)
        updateCell(newCell)
    }

    fun addNewCellValue(clickedCell: Cell, newValue: Int) {
        val newCell = clickedCell.copy(value = newValue)
        updateCell(newCell)
    }

    fun getNewSudoku() {
        viewModelScope.launch {
            getSudokuRemotely()
        }
    }
}
