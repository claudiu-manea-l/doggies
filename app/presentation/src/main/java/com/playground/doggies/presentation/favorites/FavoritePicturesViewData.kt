package com.playground.doggies.presentation.favorites

import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import com.playground.doggies.domain.model.PictureUrl

fun FavoritePictureItemViewData.toDomain() = DogPicture(
    breed = breed,
    picture = pictureUrl,
    isFavorite = true
)

fun DogPicture.fromDomain() = FavoritePictureItemViewData(
    breed = breed,
    pictureUrl = picture
)

fun List<DogPicture>.fromDomain() = FavoritePicturesViewData(
    favPics = this.map { it.fromDomain() }
)

data class FavoritePicturesViewData(
    val favPics: List<FavoritePictureItemViewData>
)

data class FavoritePictureItemViewData(
    val breed: Breed,
    val pictureUrl: PictureUrl,
)