package com.playground.doggies.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

const val API = "https://dog.ceo/api/"

interface BreedsApi {

    @GET("breed/{breed}/images")
    suspend fun getByBreed(@Path("breed") breed: String): BreedImagesDTO

    @GET("breed/{breed}/images/random")
    suspend fun getRandomBreedPicture(@Path("breed") breed: String): BreedImageDTO

    @GET("breeds/list/all")
    suspend fun getAllBreeds():BreedListDTO

}