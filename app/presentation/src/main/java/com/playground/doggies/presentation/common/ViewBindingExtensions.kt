package com.playground.doggies.presentation.common

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <VB : ViewBinding> Fragment.viewBinding(factory: (View) -> VB): ReadOnlyProperty<Fragment, VB> =
    object : ReadOnlyProperty<Fragment, VB>, DefaultLifecycleObserver {
        private var binding: VB? = null
        override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
            return binding ?: factory(thisRef.requireView()).also { viewBinding ->
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast((Lifecycle.State.INITIALIZED))) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    binding = viewBinding
                }
            }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
            viewLifecycleOwner.lifecycle.removeObserver(this)
        }
    }