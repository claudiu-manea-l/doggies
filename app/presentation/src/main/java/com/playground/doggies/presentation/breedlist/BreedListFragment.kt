package com.playground.doggies.presentation.breedlist

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.presentation.common.RecyclerViewFragment
import com.playground.doggies.presentation.common.addDoggiesMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedListFragment : RecyclerViewFragment<BreedListViewData>() {
    override val viewModel: BreedListViewModel by viewModels()
    private lateinit var adapter: BreedListAdapter

    override fun handleNewData(data: BreedListViewData) {
        adapter.submitList(data.breeds)
    }

    override fun onInitView() = with(binding) {
        addDoggiesMenu()
        adapter = BreedListAdapter {
            onItemClicked(it)
        }
        rvBreeds.adapter = adapter
    }

    private fun onItemClicked(viewData: BreedViewData) {
        when (viewData) {
            is BreedViewData.Header -> {
                navigateToDogPics(
                    Breed(viewData.breedName.lowercase())
                )
            }
            is BreedViewData.Item -> {
                navigateToDogPics(
                    viewData.breed
                )
            }
        }
    }

    private fun navigateToDogPics(breed: Breed) {
        Navigation
            .findNavController(requireView())
            .navigate(
                BreedListFragmentDirections.actionBreedListToBreedPictures(
                    breedName = breed.name,
                    subBreedName = breed.subBreed
                )
            )
    }
}

