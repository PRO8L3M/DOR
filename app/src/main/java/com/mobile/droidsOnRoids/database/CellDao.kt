package com.mobile.droidsOnRoids.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mobile.droidsOnRoids.data.entity.Cell
import kotlinx.coroutines.flow.Flow

@Dao
interface CellDao {

    @Insert
    suspend fun insertCells(cells: List<Cell>)

    @Update
    suspend fun updateCell(cell: Cell)

    @Query("SELECT * FROM Cell")
    fun getCells(): Flow<List<Cell>>

    @Query("DELETE FROM CELL")
    suspend fun clearAllTables()
}