package com.mobile.droidsOnRoids.repository

import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
import kotlinx.coroutines.flow.Flow

interface ISudokuRepository {
    suspend fun fetchSudokuBoard(): Result<List<Cell>>
    suspend fun fetchSudokuHint(uncompletedSudoku: String): Result<String>
    suspend fun fetchSudokuSolution(uncompletedSudoku: String): Result<String>

    suspend fun getSudokuBoard(): Result<Flow<List<Cell>>>
    suspend fun updateCell(cell: Cell): Result<Unit>
    suspend fun clearAllTables(): Result<Unit>
}