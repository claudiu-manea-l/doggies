package com.playground.doggies.presentation.breedlist

import com.playground.doggies.domain.model.Breed

data class BreedListViewData(
    val breeds: List<BreedViewData>
)

const val HEADER = 0
const val ITEM = 1

sealed class BreedViewData(val viewType: Int) {
    data class Header(val breedName: String) : BreedViewData(HEADER)
    data class Item(val breed: Breed) : BreedViewData(ITEM)
}

fun Breed.fromDomain() = BreedViewData.Item(breed = this)

fun List<Breed>.fromDomain(): BreedListViewData {
    val tempList = mutableListOf<BreedViewData>()
    var currentBreed = get(0).name
    tempList.add(BreedViewData.Header(currentBreed))
    forEach {
        if (it.name != currentBreed) {
            currentBreed = it.name
            tempList.add(BreedViewData.Header(currentBreed))
        }
        tempList.add(it.fromDomain())
    }
    return BreedListViewData(
        breeds = tempList
    )
}