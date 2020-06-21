package com.mobile.droidsOnRoids.data.dataSource.remote

import com.mobile.droidsOnRoids.data.network.SudokuApi

class RemoteDataSource(private val sudokuApi: SudokuApi) {
    suspend fun fetchSudoku() = sudokuApi.fetchSudoku()
    suspend fun fetchSudokuSolution(uncompletedSudoku: String) = sudokuApi.fetchSudokuSolution(uncompletedSudoku)
}
