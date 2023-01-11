package com.playground.doggies.data.remote

import com.playground.doggies.domain.DoggiesRemoteStore
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import com.playground.doggies.domain.model.PictureUrl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoggiesClient @Inject constructor(
    private val doggiesApi: BreedsApi
) : DoggiesRemoteStore {

    override suspend fun getDogBreeds(): List<Breed> {
        return doggiesApi
            .getAllBreeds()
            .toDomain()
    }

    override suspend fun getRandomDogPicForBreed(breed: Breed): DogPicture {
        return doggiesApi
            .getRandomBreedPicture(breed.name)
            .toDomain(breed)

    }

    override suspend fun getBreedPics(breed: Breed): List<DogPicture> {
        return doggiesApi
            .getByBreed(breed.name)
            .toDomain(breed)
            .filter {
                if (breed.subBreed.isNotEmpty()) it.breed.subBreed == breed.subBreed
                else true
            }
    }
}

fun BreedListDTO.toDomain(): List<Breed> {
    val breedList = mutableListOf<Breed>()
    message.forEach {
        val breed = it.key
        it.value.forEach { subBreed ->
            breedList.add(Breed(breed, subBreed))
        }
    }
    return breedList
}

fun BreedImageDTO.toDomain(breed: Breed) =
    DogPicture(
        breed = breed,
        picture = PictureUrl(message)
    )

fun BreedImagesDTO.toDomain(breed: Breed): List<DogPicture> {
    return message.map {
        DogPicture(
            breed = parseBreed(it, breed),
            picture = PictureUrl(it)
        )
    }
}

fun parseBreed(url: String, breed: Breed): Breed {
    return "breeds\\/(.*)\\/"
        .toRegex()
        .find(url)
        ?.groups
        ?.get(1)
        ?.value
        ?.split("-")
        ?.let {
            if (it.size > 1) {
                breed.copy(
                    subBreed = it[1]
                )
            } else {
                breed
            }
        } ?: breed

}