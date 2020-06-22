package com.mobile.droidsOnRoids.data.network.error

sealed class SudokuError : SudokuException() {
    data class NetworkError(override val originalException: Throwable? = null) : SudokuError()
    data class DatabaseError(override val originalException: Throwable? = null) : SudokuError()
}