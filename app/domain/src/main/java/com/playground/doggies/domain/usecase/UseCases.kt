package com.playground.doggies.domain.usecase

import com.playground.doggies.domain.DoggiesLocalStore
import com.playground.doggies.domain.DoggiesRemoteStore
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllBreeds @Inject constructor(
    private val remoteStore: DoggiesRemoteStore
) {
    suspend operator fun invoke(): List<Breed> {
        return remoteStore.getDogBreeds()
    }
}

class GetDogPictures @Inject constructor(
    private val remoteStore: DoggiesRemoteStore,
    private val getFavoritePictures: GetFavoritePictures
) {
    suspend operator fun invoke(breed: Breed): Flow<List<DogPicture>> {
        val pics = remoteStore.getBreedPics(breed)
        return getFavoritePictures()
            .map { favs ->
                pics.map { pic ->
                    val foundFav = favs.find { pic.breed.equals(it.breed) && pic.picture.equals(it.picture) }
                    return@map foundFav ?: pic
                }
            }
    }
}

class GetFavoritePictures @Inject constructor(
    private val localStore: DoggiesLocalStore
) {
    operator fun invoke(): Flow<List<DogPicture>> {
        return localStore.getFavorites()
    }
}

class SetDogPictureAsFavorite @Inject constructor(
    private val localStore: DoggiesLocalStore
) {
    suspend operator fun invoke(dogPicture: DogPicture) {
        return localStore.addFavorite(dogPicture)
    }
}

class RemoveDogPictureFromFavorite @Inject constructor(
    private val localStore: DoggiesLocalStore
) {
    suspend operator fun invoke(dogPicture: DogPicture) {
        return localStore.removeFavorite(dogPicture)
    }
}
