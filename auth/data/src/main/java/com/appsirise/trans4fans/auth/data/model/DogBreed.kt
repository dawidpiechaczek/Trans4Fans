package com.appsirise.trans4fans.auth.data.model

import com.squareup.moshi.Json

data class DogBreed(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "name")
    val name: String
)
