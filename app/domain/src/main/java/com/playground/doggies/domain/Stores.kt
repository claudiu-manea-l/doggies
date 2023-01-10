package com.playground.doggies.domain

import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import kotlinx.coroutines.flow.Flow

interface DoggiesRemoteStore {
    suspend fun getDogBreeds(): List<Breed>
    suspend fun getRandomDogPicForBreed(breed: Breed): DogPicture
    suspend fun getBreedPics(breed: Breed): List<DogPicture>
}

interface DoggiesLocalStore {
    fun getFavorites(): Flow<List<DogPicture>>
    suspend fun addFavorite(picture: DogPicture)
    suspend fun removeFavorite(picture: DogPicture)
}