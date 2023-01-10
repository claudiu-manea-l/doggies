package com.playground.doggies.presentation.breedpics

import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import com.playground.doggies.domain.model.PictureUrl

class BreedPicsListViewData(
    val breedPics: List<BreedPicsItemViewData>
)

sealed class BreedPicsItemViewData(val viewType: Int) {
    data class Header(val breedName: String) : BreedPicsItemViewData(0)
    data class Picture(
        val breed: Breed,
        val pictureUrl: PictureUrl,
        val isFavorite: Boolean
    ) : BreedPicsItemViewData(1)
}

fun BreedPicsItemViewData.Picture.toDomain(): DogPicture {
    return DogPicture(
        breed = breed,
        picture = pictureUrl,
        isFavorite = isFavorite
    )
}

fun DogPicture.fromDomain(): BreedPicsItemViewData {
    return BreedPicsItemViewData.Picture(
        breed = breed,
        pictureUrl = picture,
        isFavorite = isFavorite
    )
}

fun List<DogPicture>.fromDomain(shouldBuildHeaders: Boolean): BreedPicsListViewData {
    val picList = if (shouldBuildHeaders) {
        val tempList = mutableListOf<BreedPicsItemViewData>()
        var currentBreed = get(0).breed.toString()
        tempList.add(BreedPicsItemViewData.Header(currentBreed))
        forEach {
            if (it.breed.toString() != currentBreed) {
                currentBreed = it.breed.toString()
                tempList.add(BreedPicsItemViewData.Header(currentBreed))
            }
            tempList.add(it.fromDomain())
        }
        tempList

    } else {
        map { it.fromDomain() }
    }
    return BreedPicsListViewData(
        breedPics = picList
    )
}