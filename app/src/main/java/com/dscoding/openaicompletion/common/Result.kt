package com.dscoding.openaicompletion.common

sealed class Result<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(error: String, data: T? = null) : Result<T>(data, error)
}