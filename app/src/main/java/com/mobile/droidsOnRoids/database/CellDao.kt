package com.mobile.droidsOnRoids.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobile.droidsOnRoids.data.entity.Cell

@Dao
interface CellDao {

    @Insert
    suspend fun insertCells(cells: List<Cell>)

    @Update
    suspend fun updateCell(cell: Cell)

    @Query("SELECT * FROM Cell WHERE isSolution = 0")
    suspend fun getSudoku(): List<Cell>

    @Query("SELECT * FROM Cell WHERE isSolution = 1")
    suspend fun getSolution(): List<Cell>

    @Query("DELETE FROM CELL")
    suspend fun clearTable()
}