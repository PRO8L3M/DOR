package com.mobile.droidsOnRoids.data.network.error

sealed class SudokuError : SudokuException() {
    data class Network(override val originalException: Throwable? = null) : SudokuError()
    data class Database(override val originalException: Throwable? = null) : SudokuError()
    data class Unknown(override val originalException: Throwable? = null) : SudokuError()
}