package com.playground.doggies.presentation.breedpics

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.presentation.common.RecyclerViewFragment
import com.playground.doggies.presentation.common.addDoggiesMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedPicsFragment : RecyclerViewFragment<BreedPicsListViewData>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(BreedPicsFragmentArgs.fromBundle(requireArguments())) {
            viewModel.fetchData(
                Breed(
                    name = breedName,
                    subBreed = subBreedName
                )
            )
        }
    }

    override val viewModel: BreedPicsViewModel by viewModels()
    private lateinit var adapter: BreedPicsAdapter

    override fun onInitView() = with(binding) {
        addDoggiesMenu()
        adapter = BreedPicsAdapter {
            if (it.isFavorite) {
                viewModel.removeFavorite(it.toDomain())
            } else {
                viewModel.addFavorite(it.toDomain())
            }
        }
        rvBreeds.adapter = adapter

    }

    override fun handleNewData(data: BreedPicsListViewData) {
        adapter.submitList(data.breedPics)
    }
}

