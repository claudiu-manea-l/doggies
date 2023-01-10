package com.playground.doggies.presentation.favorites

import androidx.lifecycle.viewModelScope
import com.playground.doggies.domain.usecase.GetFavoritePictures
import com.playground.doggies.domain.usecase.RemoveDogPictureFromFavorite
import com.playground.doggies.presentation.common.DoggiesViewModel
import com.playground.doggies.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePicturesViewModel @Inject constructor(
    private val getFavoritePictures: GetFavoritePictures,
    private val removeDogPictureFromFavorite: RemoveDogPictureFromFavorite
) : DoggiesViewModel<FavoritePicturesViewData>() {

    init {
        viewModelScope.launch {
            getFavoritePictures().collectLatest {
                _state.value = ViewState.ViewData(
                    viewData = it.fromDomain()
                )
            }
        }
    }

    fun onPictureClicked(item: FavoritePictureItemViewData) {
        viewModelScope.launch {
            removeDogPictureFromFavorite(
                item.toDomain()
            )
        }
    }
}