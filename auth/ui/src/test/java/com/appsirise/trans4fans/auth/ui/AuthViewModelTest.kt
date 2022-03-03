package com.appsirise.trans4fans.auth.ui

import com.appsirise.core.ui.TestCoroutineRule
import com.appsirise.trans4fans.auth.ui.usecase.GetDogBreedsUseCase
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

internal class AuthViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @MockK
    lateinit var getDogBreedsUseCase: GetDogBreedsUseCase

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
