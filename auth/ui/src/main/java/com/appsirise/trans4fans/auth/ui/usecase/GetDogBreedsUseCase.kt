package com.appsirise.trans4fans.auth.ui.usecase

import com.appsirise.core.ui.base.BaseUseCase
import com.appsirise.trans4fans.auth.data.model.DogBreed
import com.appsirise.trans4fans.auth.ui.repository.ExampleSource
import javax.inject.Inject

internal class GetDogBreedsUseCase @Inject constructor(
    private val exampleSource: ExampleSource
) : BaseUseCase<Unit, List<DogBreed>> {

    override suspend fun execute(data: Unit): List<DogBreed> =
        exampleSource.getDogBreeds()
}
