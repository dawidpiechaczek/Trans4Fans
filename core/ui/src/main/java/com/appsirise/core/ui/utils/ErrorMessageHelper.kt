package com.appsirise.core.ui.utils

import androidx.annotation.StringRes
import com.appsirise.core.ui.R
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLPeerUnverifiedException
import kotlin.reflect.KClass
import kotlin.reflect.jvm.jvmName

/**
 * Util class to provide proper error string id message that depends on passed [throwable]
 *
 * Custom error messages
 */
class ErrorMessageHelper(private val throwable: Throwable) {

    private val httpExceptions = mutableListOf<HttpErrorMessage>()
    private val otherExceptions = mutableListOf<ErrorMessage>()

    /**
     * Adds custom http [errorCode] to be handled with passed [messageStringRes]
     */
    fun addHttpErrorMessage(
        errorCode: Int,
        @StringRes messageStringRes: Int
    ): ErrorMessageHelper {
        httpExceptions.add(HttpErrorMessage(errorCode, messageStringRes))
        return this
    }

    /**
     * Adds custom [exception] to be handled with passed [messageStringRes]
     */
    fun <T : Exception> addErrorMessage(
        exception: T,
        @StringRes messageStringRes: Int
    ): ErrorMessageHelper {
        otherExceptions.add(ErrorMessage(exception::class, messageStringRes))
        return this
    }

    /**
     * Returns proper error message to the passed [throwable]
     */
    fun getMessageStringId(): Int {
        return when (throwable) {
            is HttpException -> getHttpErrorMessageStringId(throwable)
            else -> getExceptionErrorMessageStringId()
        }
    }

    private fun getHttpErrorMessageStringId(httpException: HttpException): Int {
        val index = httpExceptions.indexOfFirst { it.httpExceptionCode == httpException.code() }
        if (index != -1) return httpExceptions[index].errorMessageStringRes
        return when (httpException.code()) {
            NETWORK_ERROR_500 -> R.string.error_http_500
            NETWORK_ERROR_404 -> R.string.error_http_404
            NETWORK_ERROR_401 -> R.string.error_http_401
            else -> R.string.error_http_unknown
        }
    }

    private fun getExceptionErrorMessageStringId(): Int {
        val exceptionIndex =
            otherExceptions.indexOfFirst { it.exception.jvmName == throwable.javaClass.name }
        if (exceptionIndex != -1) return otherExceptions[exceptionIndex].errorMessageStringRes
        return when (throwable) {
            is ConnectException, is UnknownHostException -> R.string.error_no_internet_connection
            is SocketTimeoutException -> R.string.error_timeout
            is KotlinNullPointerException, is JsonDataException -> R.string.error_missing_data
            is SSLPeerUnverifiedException -> R.string.error_security
            else -> R.string.error_unknown
        }
    }

    private data class HttpErrorMessage(
        val httpExceptionCode: Int,
        @StringRes val errorMessageStringRes: Int
    )

    private data class ErrorMessage(
        val exception: KClass<out Exception>,
        @StringRes val errorMessageStringRes: Int
    )

    companion object {
        const val NETWORK_ERROR_500 = 500
        const val NETWORK_ERROR_404 = 404
        const val NETWORK_ERROR_401 = 401
    }
}
