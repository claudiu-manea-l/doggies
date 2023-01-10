package com.playground.doggies.presentation.common

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class ViewData<out T>(
        val viewData: T
    ) : ViewState<T>()

    data class Error(
        val errorMessage: String
    ) : ViewState<Nothing>()
}
