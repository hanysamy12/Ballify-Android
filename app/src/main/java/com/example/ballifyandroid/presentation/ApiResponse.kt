package com.example.ballifyandroid.presentation

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Failure(val message: Throwable) : ApiResponse<Nothing>()
    data object Loading : ApiResponse<Nothing>()
}
