package com.mobile.droidsOnRoids.repository

import com.mobile.droidsOnRoids.data.dataSource.local.LocalDataSource
import com.mobile.droidsOnRoids.data.dataSource.remote.RemoteDataSource
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
import com.mobile.droidsOnRoids.data.network.doOnSuccess
import com.mobile.droidsOnRoids.data.network.safeCall
import com.mobile.droidsOnRoids.ext.bodyOrException
import kotlinx.coroutines.flow.Flow

class SudokuRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : ISudokuRepository {

    override suspend fun fetchSudokuBoard() = safeCall { remoteDataSource.getSudokuBoard().bodyOrException() }.doOnSuccess { value ->
        safeCall { localDataSource.saveCells(value) }
    }

    override suspend fun fetchSudokuHint(uncompletedSudoku: String) = safeCall { remoteDataSource.getSudokuHint(uncompletedSudoku).bodyOrException() }

    override suspend fun fetchSudokuSolution(uncompletedSudoku: String) = safeCall { remoteDataSource.getSudokuSolution(uncompletedSudoku).bodyOrException() }

    override suspend fun getSudokuBoard(): Result<Flow<List<Cell>>> = safeCall { localDataSource.getCells() }

    override suspend fun updateCell(cell: Cell) = safeCall { localDataSource.updateCell(cell) }

    override suspend fun clearAllTables(): Result<Unit> = safeCall { localDataSource.clearAllTables() }

}