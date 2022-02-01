package com.appsirise.trans4fans.auth.data

import retrofit2.Response
import retrofit2.http.GET

interface ExampleApi {

    @GET("/v1/breeds?limit=10&page=0")
    suspend fun getDogBreeds(): Response<List<DogBreed>>
}
