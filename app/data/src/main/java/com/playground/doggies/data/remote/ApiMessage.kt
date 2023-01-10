package com.playground.doggies.data.remote

open class ApiMessage<T>(
    val message: T,
    val status: String
)

typealias BreedImageDTO = ApiMessage<String>
typealias BreedImagesDTO = ApiMessage<List<String>>
typealias BreedListDTO = ApiMessage<Map<String, List<String>>>