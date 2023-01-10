package com.playground.doggies.presentation.favorites

import androidx.fragment.app.viewModels
import com.playground.doggies.presentation.common.RecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePicturesFragment : RecyclerViewFragment<FavoritePicturesViewData>() {

    override val viewModel: FavoritePicturesViewModel by viewModels()
    private lateinit var adapter: FavoritePicsAdapter

    override fun onInitView() {
        adapter = FavoritePicsAdapter {
            viewModel.onPictureClicked(it)
        }
        binding.rvBreeds.adapter = adapter
    }

    override fun handleNewData(data: FavoritePicturesViewData) {
        adapter.submitList(data.favPics)
    }
}