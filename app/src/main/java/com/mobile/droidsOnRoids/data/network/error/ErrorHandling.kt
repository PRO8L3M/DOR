package com.mobile.droidsOnRoids.data.network.error

import java.io.IOException
import java.net.UnknownHostException

fun Throwable.toSudokuError(): SudokuError = when(this) {
    is IOException -> SudokuError.Network(this)
    is UnknownHostException -> SudokuError.Network(this)
    is NoSuchElementException -> SudokuError.Database(this)
    else -> SudokuError.Unknown(this)
}
