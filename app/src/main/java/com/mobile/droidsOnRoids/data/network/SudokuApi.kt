package com.mobile.droidsOnRoids.data.network

import com.mobile.droidsOnRoids.data.entity.Cell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SudokuApi {

    @GET("new")
    suspend fun fetchSudoku(): Response<List<Cell>>

    @GET("solve/{uncompletedSudoku}")
    suspend fun fetchSudokuSolution(@Path("uncompletedSudoku") uncompletedSudoku: String): Response<List<Cell>>
}
