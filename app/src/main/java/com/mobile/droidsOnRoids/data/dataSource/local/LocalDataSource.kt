package com.mobile.droidsOnRoids.data.dataSource.local

import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.database.AppDatabase

class LocalDataSource(private val db: AppDatabase) {
    suspend fun saveCells(cells: List<Cell>) = db.cellDao().insertCells(cells)
    suspend fun updateCell(cell: Cell) = db.cellDao().updateCell(cell)
    suspend fun getSudoku() = db.cellDao().getSudoku()
    suspend fun getSudokuSolution() = db.cellDao().getSolution()
    suspend fun clearAllTables() = db.cellDao().clearTable()
}
