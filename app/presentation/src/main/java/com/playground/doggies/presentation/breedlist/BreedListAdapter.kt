package com.playground.doggies.presentation.breedlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.presentation.common.capsFirst
import com.playground.doggies.presentation.databinding.IvBreedBinding
import com.playground.doggies.presentation.databinding.IvBreedHeaderBinding

private val BreedDiffUtils = object : DiffUtil.ItemCallback<BreedViewData>() {
    override fun areItemsTheSame(oldItem: BreedViewData, newItem: BreedViewData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BreedViewData, newItem: BreedViewData): Boolean {
        return oldItem == newItem
    }
}

class BreedListAdapter(
    private val onItemClick: (BreedViewData) -> Unit
) : ListAdapter<BreedViewData, BreedListAdapter.BreedViewHolder>(BreedDiffUtils) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BreedViewHolder {
        val holder = if (viewType == HEADER) {
            BreedViewHolder.BreedHeaderViewHolder(
                binding = IvBreedHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            BreedViewHolder.BreedItemViewHolder(
                binding = IvBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
        holder.itemView.setOnClickListener { view ->
            (view.tag as? BreedViewData)?.let {
                onItemClick(it)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.tag = data
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    sealed class BreedViewHolder(itemView: View) : ViewHolder(itemView) {
        abstract fun bind(data: BreedViewData)

        class BreedHeaderViewHolder(
            private val binding: IvBreedHeaderBinding
        ) : BreedViewHolder(binding.root) {
            override fun bind(data: BreedViewData) {
                if (data is BreedViewData.Header) {
                    binding.tvBreed.text = data.breedName.capsFirst()
                }
            }
        }

        class BreedItemViewHolder(
            private val binding: IvBreedBinding
        ) : BreedViewHolder(binding.root) {
            override fun bind(data: BreedViewData) {
                if (data is BreedViewData.Item) {
                    binding.tvBreedName.text = data.breed.toDisplayString()
                }
            }
        }
    }
}

private fun Breed.toDisplayString(): String {
    return name.capsFirst() + " " + subBreed.capsFirst()
}