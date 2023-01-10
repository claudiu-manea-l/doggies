package com.playground.doggies.presentation.common

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.SimpleItemAnimator
import com.playground.doggies.presentation.R
import com.playground.doggies.presentation.databinding.FragmentBreedlistBinding

abstract class RecyclerViewFragment<T> : Fragment(R.layout.fragment_breedlist) {
    protected val binding by viewBinding(FragmentBreedlistBinding::bind)
    abstract val viewModel : DoggiesViewModel<T>
    abstract fun onInitView()
    abstract fun handleNewData(data:T)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (binding.rvBreeds.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        onInitView()
        observeState()
    }

    private fun observeState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Loading -> showLoading()
                is ViewState.Error -> showError(it.errorMessage)
                is ViewState.ViewData -> handleViewData(it.viewData)
            }
        }
    }

    private fun showLoading() = with(binding) {
        pbLoading.isVisible = true
        rvBreeds.isVisible = false
        tvError.isVisible = false
    }

    private fun showError(message: String) = with(binding) {
        pbLoading.isVisible = false
        rvBreeds.isVisible = false
        tvError.isVisible = true
        tvError.text = message
    }

    private fun handleViewData(viewData: T) = with(binding) {
        pbLoading.isVisible = false
        rvBreeds.isVisible = true
        tvError.isVisible = false
        handleNewData(viewData)
    }
}

open class DoggiesViewModel<T> : ViewModel(){
    protected val _state = MutableLiveData<ViewState<T>>()
    val state: LiveData<ViewState<T>> = _state

    init {
        _state.value = ViewState.Loading
    }
}