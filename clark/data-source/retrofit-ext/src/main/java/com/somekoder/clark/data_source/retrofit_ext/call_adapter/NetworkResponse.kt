package com.somekoder.clark.data_source.retrofit_ext.call_adapter

import com.somekoder.clark.domain.NetworkResult
import java.io.IOException

sealed class NetworkResponse<out T, out U> {
    data class Success<T : Any>(val body: T) : NetworkResponse<T, Nothing>()
    data class ApiError<U : Any>(val body: U, val code: Int) : NetworkResponse<Nothing, U>()
    data class NetworkError(val error: IOException) : NetworkResponse<Nothing, Nothing>()
    data class UnknownError(val error: Throwable?) : NetworkResponse<Nothing, Nothing>()
}

fun <SuccessDto, ErrorDto, SuccessDomain, ErrorDomain>NetworkResponse<SuccessDto, ErrorDto>.toNetworkResult(convertSuccess: (SuccessDto) -> SuccessDomain, convertError: (ErrorDto) -> ErrorDomain) : NetworkResult<SuccessDomain, ErrorDomain> {
    return when (this) {
        is NetworkResponse.Success -> {
            NetworkResult.Success(data = convertSuccess(body))
        }
        is NetworkResponse.ApiError -> {
            NetworkResult.Error(error = convertError(body))
        }
        is NetworkResponse.NetworkError -> {
            NetworkResult.Exception(exception = error)
        }
        is NetworkResponse.UnknownError -> {
            NetworkResult.Exception(exception = error ?: UnknownError())
        }
    }
}


