package com.mobile.droidsOnRoids.ui.sudoku

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
import com.mobile.droidsOnRoids.data.network.doOnSuccess
import com.mobile.droidsOnRoids.repository.SudokuRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SudokuViewModel(private val repository: SudokuRepository) : ViewModel() {

    private val _sudoku: MutableLiveData<Result<List<Cell>>> by lazy { MutableLiveData<Result<List<Cell>>>() }.apply { getSudokuBoard() }
    val sudoku: LiveData<Result<List<Cell>>> = _sudoku

    private fun fetchSudokuBoard() {
        /**
         * Normally I use Dispatchers.IO for remote work and changes to Main when need to post result to LiveData
         * but I've heard from last GDG Wroclaw Webinar that retrofit knows that I use coroutines and handle that for me under the hood
         */
        viewModelScope.launch {
            repository.fetchSudokuBoard()
        }
    }

    private fun getSudokuBoard() {
        viewModelScope.launch {
            repository.getSudokuBoard().doOnSuccess { cellsFlow ->
                cellsFlow.collect { cells ->
                    if (cells.isEmpty()) fetchSudokuBoard()
                   _sudoku.value = Result.Success(cells)
                }
            }
        }
    }

    private fun updateCell(cell: Cell) {
        if (!cell.isEditable) return
        viewModelScope.launch {
            repository.updateCell(cell)
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

    fun clearAllTables() {
        viewModelScope.launch {
            repository.clearAllTables()
        }
    }

}