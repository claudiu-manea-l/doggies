package com.playground.doggies.presentation.common

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.playground.doggies.presentation.NavGraphDirections
import com.playground.doggies.presentation.R

class DoggiesMenuProvider(
    private val navController: NavController
) : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_favorites -> {
                navController.navigate(NavGraphDirections.goToFavorites())
                true
            }
            else -> false
        }
    }
}

fun Fragment.addDoggiesMenu(){
    requireActivity().addMenuProvider(
        DoggiesMenuProvider(findNavController()),
        viewLifecycleOwner,
        Lifecycle.State.RESUMED
    )
}