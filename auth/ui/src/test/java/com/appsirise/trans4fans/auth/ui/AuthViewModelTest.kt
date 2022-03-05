package com.appsirise.trans4fans.auth.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.trans4fans.auth.data.model.DogBreed
import com.appsirise.trans4fans.auth.ui.usecase.GetDogBreedsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AuthViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getDogBreedsUseCase: GetDogBreedsUseCase

    @RelaxedMockK
    lateinit var dogBreedsObserver: Observer<ViewState<List<DogBreed>>>

    private lateinit var authViewModel: AuthViewModel

    @Before
    fun before() {
        MockKAnnotations.init(this)
        authViewModel = AuthViewModel(getDogBreedsUseCase)
    }

    @Test
    fun `Should emit dog breeds list if succeeded`() = coroutineRule.runTest {
        // Given
        val dogBreeds = listOf(DogBreed(1L, "Akita"))

        coEvery { getDogBreedsUseCase.execute(Unit) } returns dogBreeds
        // When
        authViewModel.getBreedsLiveData.observeForever(dogBreedsObserver)
        authViewModel.getBreeds()

        // Then
        verify { dogBreedsObserver.onChanged(ViewState.Success(dogBreeds)) }
        confirmVerified(dogBreedsObserver)
    }
}
