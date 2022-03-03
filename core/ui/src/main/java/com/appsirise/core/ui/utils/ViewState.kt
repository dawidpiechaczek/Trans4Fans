package com.appsirise.core.ui.utils

import androidx.annotation.StringRes

sealed class ViewState<out RESULT : Any> {

    data class Success<out RESULT : Any>(val data: RESULT) : ViewState<RESULT>()

    data class Error(@StringRes val errorMessage: Int) : ViewState<Nothing>()

    fun isError(): Boolean = this is Error

    fun isSuccess(): Boolean = this is Success

    fun getSuccessResult(): RESULT? =
        if (this is Success) {
            this.data
        } else {
            null
        }
}
