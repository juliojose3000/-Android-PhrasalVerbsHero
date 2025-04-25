package com.loaizasoftware.phrasalverbshero.core.network

fun <T> safeApiCall(apiCall: () -> T): ApiResult<T> {
    return try {
        val result = apiCall()
        ApiResult.Success(result)
    } catch (throwable: Throwable) {
        when (throwable) {
            is retrofit2.HttpException -> {
                ApiResult.HttpError(throwable.code(), throwable.message())
            }
            is java.io.IOException -> {
                ApiResult.NetworkError(throwable)
            }
            else -> {
                ApiResult.UnknownError(throwable)
            }
        }
    }
}

