package com.mobile.droidsOnRoids.repository

import com.mobile.droidsOnRoids.data.dataSource.local.LocalDataSource
import com.mobile.droidsOnRoids.data.dataSource.remote.RemoteDataSource
import com.mobile.droidsOnRoids.data.entity.Cell
import com.mobile.droidsOnRoids.data.network.Result
import com.mobile.droidsOnRoids.data.network.doOnSuccess
import com.mobile.droidsOnRoids.data.network.safeCall
import com.mobile.droidsOnRoids.ext.bodyOrException
import com.mobile.droidsOnRoids.ext.convertToSudokuChain
import com.mobile.droidsOnRoids.ext.throwIfEmpty

class SudokuRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : ISudokuRepository {

    private suspend fun fetchSudoku() = safeCall {
        remoteDataSource.fetchSudoku().bodyOrException()
    }.doOnSuccess { cells ->
        saveCellsToDatabase(cells)
        fetchSudokuSolution(cells.convertToSudokuChain())
    }

    private suspend fun fetchSudokuSolution(uncompletedSudoku: String) =
        safeCall {
            remoteDataSource.fetchSudokuSolution(uncompletedSudoku).bodyOrException()
        }.doOnSuccess { saveCellsToDatabase(it) }


    override suspend fun getSudoku(): Result<List<Cell>> {
        val localResult = safeCall { localDataSource.getSudoku().throwIfEmpty() }
        return if (localResult is Result.Failure) {
            safeCall {
                val remoteResult = fetchSudoku()
                if (remoteResult is Result.Failure) throw remoteResult.exception
                localDataSource.getSudoku().throwIfEmpty()
            }
        } else {
            localResult
        }
    }

    override suspend fun getSudokuSolution(): Result<List<Cell>> =
        safeCall { localDataSource.getSudokuSolution() }

    private suspend fun saveCellsToDatabase(cells: List<Cell>) =
        safeCall { localDataSource.saveCells(cells) }

    override suspend fun updateCell(cell: Cell) = safeCall { localDataSource.updateCell(cell) }

    override suspend fun clearTable() = safeCall { localDataSource.clearAllTables() }
}