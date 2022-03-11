package com.jalexy.meme.favorite.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jalexy.meme.databinding.ItemMemeFavotiteBinding
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(callback: FavoriteItemDiffCallback) :
    ListAdapter<Meme, FavoriteItemViewHolder>(callback) {

    var changeFragmentClickListener: ((Meme) -> Unit)? = null
    var deleteToDataBaseClickListener: ((Meme) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemViewHolder {
        val binding =
            ItemMemeFavotiteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return FavoriteItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: FavoriteItemViewHolder, position: Int) {
        val memeItem = getItem(position)
        viewHolder.bind(
            memeItem,
            changeFragmentClickListener,
            deleteToDataBaseClickListener

        )
    }
}