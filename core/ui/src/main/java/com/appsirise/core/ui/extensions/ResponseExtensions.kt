package com.appsirise.core.ui.extensions

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.getBodyOrThrowException(): T {
    val body = body()
    return when {
        isSuccessful && body != null -> body
        isSuccessful && body == null -> throw KotlinNullPointerException("Response body is null")
        else -> throw HttpException(this)
    }
}
