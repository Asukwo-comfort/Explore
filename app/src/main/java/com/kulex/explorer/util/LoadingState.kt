package com.kulex.explorer.util

sealed class LoadingState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : LoadingState<T>(data)
    class Error<T>(message: String, data: T? = null) : LoadingState<T>(data, message)
    class Loading<T> : LoadingState<T>()
}

