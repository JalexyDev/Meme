package com.jalexy.meme.memelist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class MemeItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.getItemId() == newItem.getItemId()
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return if (oldItem.getItemId() == Loader.ID || newItem.getItemId() == Loader.ID) {
            false
        } else {
            if (oldItem is Meme && newItem is Meme) {
                oldItem == newItem
            }
            false
        }