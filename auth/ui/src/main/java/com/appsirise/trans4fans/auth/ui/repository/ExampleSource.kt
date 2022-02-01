package com.appsirise.trans4fans.auth.ui.repository

import com.appsirise.trans4fans.auth.data.DogBreed

interface ExampleSource {
    suspend fun getDogBreeds(): List<DogBreed>
}