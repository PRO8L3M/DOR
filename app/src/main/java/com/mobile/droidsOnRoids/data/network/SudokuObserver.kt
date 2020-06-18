package com.mobile.droidsOnRoids.data.network

import androidx.lifecycle.Observer

class SudokuObserver<T : Any>(
    private val onSuccess: (T) -> Unit,
    private val onFailure: (Exception) -> Unit,
    private val onLoading: () -> Unit
) : Observer<Result<T>> {

    override fun onChanged(result: Result<T>) = when(result) {
        is Result.Success -> onSuccess(result.data)
        is Result.Failure -> onFailure(result.exception)
        is Result.Loading -> onLoading()
    }
}
