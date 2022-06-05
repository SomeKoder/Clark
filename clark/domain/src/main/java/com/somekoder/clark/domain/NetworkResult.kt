package com.somekoder.clark.domain

sealed class NetworkResult<out T, out E> {

    data class Success<T>(val data: T) : NetworkResult<T, Nothing>()

    data class Error<E>(val error: E) : NetworkResult<Nothing, E>()

    data class Exception(val exception: Throwable) : NetworkResult<Nothing, Nothing>()
}