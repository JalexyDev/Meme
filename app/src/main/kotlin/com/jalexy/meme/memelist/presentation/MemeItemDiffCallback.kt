package com.jalexy.meme.memelist.presentation

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class MemeItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.getItemId() == newItem.getItemId()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return when {
            oldItem.getItemId() == Loader.ID || newItem.getItemId() == Loader.ID -> false
            oldItem is Meme && newItem is Meme -> oldItem == newItem
            else -> false
        }
    }
}