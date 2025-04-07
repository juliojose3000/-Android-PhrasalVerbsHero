package com.loaizasoftware.phrasalverbshero.core.network

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class HttpError(val code: Int, val message: String?) : ApiResult<Nothing>()
    data class NetworkError(val throwable: Throwable) : ApiResult<Nothing>()
    data class UnknownError(val throwable: Throwable) : ApiResult<Nothing>()
}

enum class ApiResponse() {
    Success,
    HttpError,
    NetworkError,
    UnknownError

}