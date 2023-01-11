package com.playground.doggies.presentation.favorites

import androidx.lifecycle.viewModelScope
import com.playground.doggies.domain.usecase.GetFavoritePictures
import com.playground.doggies.domain.usecase.RemoveDogPictureFromFavorite
import com.playground.doggies.presentation.common.DoggiesViewModel
import com.playground.doggies.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePicturesViewModel @Inject constructor(
    private val getFavoritePictures: GetFavoritePictures,
    private val removeDogPictureFromFavorite: RemoveDogPictureFromFavorite
) : DoggiesViewModel<FavoritePicturesViewData>() {

    private val searchQuery = MutableStateFlow("")

    private val dataFlow = getFavoritePictures().combine(
        searchQuery
    ) { data, filter ->
        ViewState.ViewData(
            viewData = data.filter { it.breed.toString().contains(filter) }
                .fromDomain()
        )
    }

    init {
        viewModelScope.launch {
            dataFlow.collectLatest {
                _state.value = it
            }
        }
    }

    fun onFilterPics(query: String) {
        searchQuery.compareAndSet(searchQuery.value,query)
    }

    fun onPictureClicked(item: FavoritePictureItemViewData) {
        viewModelScope.launch {
            removeDogPictureFromFavorite(
                item.toDomain()
            )
        }
    }
}