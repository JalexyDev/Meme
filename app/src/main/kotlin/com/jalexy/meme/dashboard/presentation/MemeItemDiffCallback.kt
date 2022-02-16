package com.jalexy.meme.dashboard.presentation

import androidx.recyclerview.widget.DiffUtil
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class MemeItemDiffCallback @Inject constructor(): DiffUtil.ItemCallback<Meme>() {
    override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
        return oldItem == newItem
    }
}