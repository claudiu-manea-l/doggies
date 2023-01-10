package com.playground.doggies.presentation.breedpics

import androidx.lifecycle.viewModelScope
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.DogPicture
import com.playground.doggies.domain.usecase.GetDogPictures
import com.playground.doggies.domain.usecase.RemoveDogPictureFromFavorite
import com.playground.doggies.domain.usecase.SetDogPictureAsFavorite
import com.playground.doggies.presentation.common.DoggiesViewModel
import com.playground.doggies.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedPicsViewModel @Inject constructor(
    private val getDogPictures: GetDogPictures,
    private val setDogPictureAsFavorite: SetDogPictureAsFavorite,
    private val removeDogPictureFromFavorite: RemoveDogPictureFromFavorite
) : DoggiesViewModel<BreedPicsListViewData>() {

    fun fetchData(breed: Breed) {
        _state.value = ViewState.Loading
        viewModelScope.launch {
            getDogPictures(breed).collectLatest {
                _state.value = ViewState.ViewData(
                    viewData = it.fromDomain(breed.subBreed.isEmpty())
                )
            }
        }
    }

    fun removeFavorite(dogPicture: DogPicture) {
        viewModelScope.launch {
            removeDogPictureFromFavorite(dogPicture)
        }
    }

    fun addFavorite(dogPicture: DogPicture) {
        viewModelScope.launch {
            setDogPictureAsFavorite(dogPicture)
        }
    }
}