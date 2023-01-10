package com.playground.doggies.domain.model

data class Breed(
    val name: String,
    val subBreed : String = ""
) {
    override fun toString(): String {
        if(subBreed.isEmpty()) return name
        return "$name $subBreed"
    }
}

data class PictureUrl(
    val link: String
)

data class DogPicture(
    val breed: Breed,
    val picture: PictureUrl,
    val isFavorite:Boolean = false
)
