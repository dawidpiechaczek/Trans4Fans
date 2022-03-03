package com.appsirise.trans4fans.auth.ui.repository

import com.appsirise.trans4fans.auth.data.model.DogBreed

internal interface ExampleSource {
    suspend fun getDogBreeds(): List<DogBreed>
}
