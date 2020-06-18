package com.mobile.droidsOnRoids.data.dataSource.remote

import com.mobile.droidsOnRoids.data.network.SudokuApi

class RemoteDataSource(private val sudokuApi: SudokuApi) {
    suspend fun getSudokuBoard() = sudokuApi.getSudokuBoard()
    suspend fun getSudokuHint(uncompletedSudoku: String) = sudokuApi.getSudokuHint(uncompletedSudoku)
    suspend fun getSudokuSolution(uncompletedSudoku: String) = sudokuApi.getSudokuSolution(uncompletedSudoku)
}
