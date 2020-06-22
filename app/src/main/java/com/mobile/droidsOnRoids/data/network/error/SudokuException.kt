package com.mobile.droidsOnRoids.data.network.error

abstract class SudokuException : Exception() {
    abstract val originalException: Throwable?
}