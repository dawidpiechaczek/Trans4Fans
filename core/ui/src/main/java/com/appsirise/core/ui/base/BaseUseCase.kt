package com.appsirise.core.ui.base

interface BaseUseCase<R, T> {
    suspend fun execute(data: R): T
}
