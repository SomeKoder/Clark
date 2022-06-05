package com.somekoder.clark.domain

sealed class DataState<out T, out E> {

    data class Data<T>(
        val value: T
    ) : DataState<T, Nothing>()

    data class ErrorData<E>(
        val error: E
    ) : DataState<Nothing, E>()

    data class Exception(
        val exception: Throwable
    ) : DataState<Nothing, Nothing>()

    data class Loading(
        val isLoading: Boolean
    ) : DataState<Nothing, Nothing>()
}
