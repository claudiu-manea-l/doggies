package com.playground.doggies.presentation.breedlist

import androidx.lifecycle.viewModelScope
import com.playground.doggies.domain.usecase.GetAllBreeds
import com.playground.doggies.presentation.common.DoggiesViewModel
import com.playground.doggies.presentation.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val getAllBreeds: GetAllBreeds
) : DoggiesViewModel<BreedListViewData>() {

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val breeds = getAllBreeds()
            _state.value = ViewState.ViewData(
                breeds.fromDomain()
            )
        }
    }
}