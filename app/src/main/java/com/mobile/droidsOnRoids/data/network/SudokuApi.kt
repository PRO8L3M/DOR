package com.mobile.droidsOnRoids.data.network

import com.mobile.droidsOnRoids.data.entity.Cell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SudokuApi {

    @GET("new")
    suspend fun getSudokuBoard(): Response<List<Cell>>

    @GET("hint/{sudokuChain}")
    suspend fun getSudokuHint(@Path("uncompletedSudoku") uncompletedSudoku: String): Response<String>

    @GET("solve/{sudokuChain}")
    suspend fun getSudokuSolution(@Path("uncompletedSudoku") uncompletedSudoku: String): Response<String>
}