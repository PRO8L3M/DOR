package com.mobile.droidsOnRoids.data.network.error

import java.io.IOException
import java.net.UnknownHostException

fun Throwable.toSudokuError(): SudokuError = when(this) {
    is IOException -> toSudokuError()
    is NoSuchElementException -> SudokuError.DatabaseError(this)
    else -> throw this
}

fun IOException.toSudokuError() = when(this) {
    is UnknownHostException -> SudokuError.NetworkError(this)
    else -> throw this
}
