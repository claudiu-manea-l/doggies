package com.playground.doggies.presentation.breedpics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.playground.doggies.presentation.databinding.IvBreedHeaderBinding
import com.playground.doggies.presentation.databinding.IvBreedPicsBinding

private val BreedPicsDiffUtils = object : DiffUtil.ItemCallback<BreedPicsItemViewData>() {
    override fun areItemsTheSame(
        oldItem: BreedPicsItemViewData,
        newItem: BreedPicsItemViewData
    ): Boolean {
        if (oldItem.viewType != newItem.viewType) return false
        if (oldItem is BreedPicsItemViewData.Header && newItem is BreedPicsItemViewData.Header) {
            return oldItem.breedName == newItem.breedName
        }
        if (oldItem is BreedPicsItemViewData.Picture && newItem is BreedPicsItemViewData.Picture) {
            return oldItem.pictureUrl.link == newItem.pictureUrl.link
        }
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: BreedPicsItemViewData,
        newItem: BreedPicsItemViewData
    ): Boolean {
        if (oldItem is BreedPicsItemViewData.Header && newItem is BreedPicsItemViewData.Header) {
            return oldItem.breedName == newItem.breedName
        }
        if (oldItem is BreedPicsItemViewData.Picture && newItem is BreedPicsItemViewData.Picture) {
            return oldItem.pictureUrl.link == newItem.pictureUrl.link && oldItem.isFavorite == newItem.isFavorite
        }
        return oldItem == newItem
    }
}

class BreedPicsAdapter(
    private val onPictureClicked: (BreedPicsItemViewData.Picture) -> Unit
) : ListAdapter<BreedPicsItemViewData, BreedPicsAdapter.BreedPicsViewHolder>(BreedPicsDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedPicsViewHolder {
        val holder = if (viewType == 0) {
            BreedPicsViewHolder.BreedHeaderViewHolder(
                binding = IvBreedHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            BreedPicsViewHolder.BreedItemViewHolder(
                binding = IvBreedPicsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
        attachListener(holder)
        return holder
    }

    override fun onBindViewHolder(holder: BreedPicsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    private fun attachListener(holder: BreedPicsViewHolder) {
        holder.itemView.setOnClickListener { view ->
            (view.tag as? BreedPicsItemViewData.Picture)?.let {
                onPictureClicked(it)
            }
        }
    }

    sealed class BreedPicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: BreedPicsItemViewData)
        class BreedHeaderViewHolder(
            private val binding: IvBreedHeaderBinding
        ) : BreedPicsViewHolder(binding.root) {
            override fun bind(data: BreedPicsItemViewData) {
                if (data is BreedPicsItemViewData.Header) {
                    binding.tvBreed.text = data.breedName
                }
            }
        }

        class BreedItemViewHolder(
            private val binding: IvBreedPicsBinding
        ) : BreedPicsViewHolder(binding.root) {
            override fun bind(data: BreedPicsItemViewData) = with(binding){
                if (data is BreedPicsItemViewData.Picture) {
                    itemView.tag = data
                    tvBreedName.text = data.breed.toString()
                    ivFavorite.isChecked = data.isFavorite
                    if (binding.ivPic.tag == null || binding.ivPic.tag != data.pictureUrl.link) {
                        loadDogPic(data.pictureUrl.link)
                    }
                }
            }

            private fun loadDogPic(link: String) {
                Glide.with(itemView.context)
                    .load(link)
                    .error(android.R.drawable.ic_menu_gallery)
                    .centerInside()
                    .into(binding.ivPic)
                binding.ivPic.tag = link
            }
        }
    }
}