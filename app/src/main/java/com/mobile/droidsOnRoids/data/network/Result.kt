package com.mobile.droidsOnRoids.data.network

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: Exception): Result<Nothing>()
    object Loading : Result<Nothing>()
}

inline fun <T> safeCall(call: () -> T): Result<T> =
    try {
        Result.Success(call())
    } catch (exception: Exception) {
        Result.Failure(exception)
    }

inline infix fun <T> Result<T>.doOnSuccess(f: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        f(data)
    }
    return this
}