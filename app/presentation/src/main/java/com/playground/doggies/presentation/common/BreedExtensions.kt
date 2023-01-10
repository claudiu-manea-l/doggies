package com.playground.doggies.presentation.common

import com.playground.doggies.domain.model.Breed
import java.util.*

fun Breed.applyCaps() = Breed(
    name = name.capsFirst(),
    subBreed = subBreed.capsFirst()
)

fun String.capsFirst(): String {
    return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}