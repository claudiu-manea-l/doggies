package com.playground.doggies.presentation.favorites

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.playground.doggies.presentation.R
import com.playground.doggies.presentation.common.RecyclerViewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePicturesFragment : RecyclerViewFragment<FavoritePicturesViewData>() {

    override val viewModel: FavoritePicturesViewModel by viewModels()
    private lateinit var adapter: FavoritePicsAdapter

    override fun onInitView() {
        addSearchMenu { viewModel.onFilterPics(it) }
        adapter = FavoritePicsAdapter {
            viewModel.onPictureClicked(it)
        }
        binding.rvBreeds.adapter = adapter
    }

    override fun handleNewData(data: FavoritePicturesViewData) {
        adapter.submitList(data.favPics)
    }
}

private fun Fragment.addSearchMenu(onSearch: (String) -> Unit) {
    requireActivity()
        .addMenuProvider(
            SearchMenuProvider {
                onSearch(it)
            }
        )
}

class SearchMenuProvider(
    private val onSearchText: (String) -> Unit
) : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_serach, menu)
        menu.findItem(R.id.action_search)
            .actionView
            .let { it as? SearchView }
            ?.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        onSearchText(query ?: "")
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        onSearchText(newText ?: "")
                        return true
                    }
                }
            )

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return menuItem.itemId == R.id.action_search
    }
}