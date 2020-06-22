package com.mobile.droidsOnRoids.data.network

import com.mobile.droidsOnRoids.common.NO_INTERNET_CONNECTION
import com.mobile.droidsOnRoids.data.network.error.SudokuError
import com.mobile.droidsOnRoids.data.network.error.toSudokuError
import org.koin.java.KoinJavaComponent.inject

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T): Result<T>()
    data class Failure(val exception: SudokuError): Result<Nothing>()
}

/*inline fun <T> safeCall(call: () -> T): Result<T> =
    try {
        val connectivityManager by inject(ConnectionManagerImpl::class.java)
        when(connectivityManager.hasNetworkConnection()) {
            true -> Result.Success(call())
            else -> throw IllegalStateException(NO_INTERNET_CONNECTION)
        }
    } catch (exception: Exception) {
        Result.Failure(exception.toSudokuError())
    }*/

// todo for test
inline fun <T> safeCall(call: () -> T): Result<T> =
    try {
        Result.Success(call())
    } catch (exception: Exception) {
        Result.Failure(exception.toSudokuError())
    }

inline fun <T> Result<T>.doOnSuccess(f: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        f(data)
    }
    return this
}

inline fun <T> Result<T>.doOnFailure(f: (Exception) -> Unit): Result<T> {
    if (this is Result.Failure) {
        f(exception)
    }
    return this
}
