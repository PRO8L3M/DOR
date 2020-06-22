package com.mobile.droidsOnRoids.repository

import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result

interface ISudokuRepository {
    suspend fun fetchSudoku(): Result<List<Cell>>
    suspend fun getSudoku(): Result<List<Cell>>
    suspend fun getSudokuSolution(): Result<List<Cell>>
    suspend fun updateCell(cell: Cell): Result<Unit>
}
