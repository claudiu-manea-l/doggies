package com.playground.doggies.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.playground.doggies.presentation.databinding.IvBreedPicsBinding

private val FavoriteBreedDiffUtils = object : DiffUtil.ItemCallback<FavoritePictureItemViewData>() {
    override fun areItemsTheSame(
        oldItem: FavoritePictureItemViewData,
        newItem: FavoritePictureItemViewData
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: FavoritePictureItemViewData,
        newItem: FavoritePictureItemViewData
    ): Boolean {
        return oldItem == newItem
    }
}

class FavoritePicsAdapter(
    private val onPictureClicked: (FavoritePictureItemViewData) -> Unit
) : ListAdapter<FavoritePictureItemViewData, FavoritePicsAdapter.FavoritePicsViewHolder>(
    FavoriteBreedDiffUtils
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePicsViewHolder {
        return FavoritePicsViewHolder(
            IvBreedPicsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).also { holder ->
            holder.itemView.setOnClickListener { view ->
                (view.tag as? FavoritePictureItemViewData)?.let {
                    onPictureClicked(it)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FavoritePicsViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class FavoritePicsViewHolder(
        private val binding: IvBreedPicsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FavoritePictureItemViewData) = with(binding) {
            itemView.tag = data
            tvBreedName.text = data.breed.toString()
            ivFavorite.isChecked = true
            Glide.with(itemView.context)
                .load(data.pictureUrl.link)
                .error(android.R.drawable.ic_menu_gallery)
                .into(binding.ivPic)

        }
    }
}