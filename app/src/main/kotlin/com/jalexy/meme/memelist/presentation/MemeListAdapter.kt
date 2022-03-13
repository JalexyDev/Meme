package com.jalexy.meme.memelist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jalexy.meme.databinding.ItemMemeBinding
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class MemeListAdapter @Inject constructor(callback: MemeItemDiffCallback) :
    ListAdapter<Meme, MemeItemViewHolder>(callback) {

    lateinit var changeFragmentClickListener: ((Meme) -> Unit)
    lateinit var addToDataBaseClickListener: ((Meme, callback: (item: Meme) -> Unit) -> Meme)
    var isReadyLoader: () -> Boolean = {
        false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MemeItemViewHolder(ItemMemeBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(viewHolder: MemeItemViewHolder, position: Int) {
        val memeItem = getItem(position)
        viewHolder.bind(
            memeItem,
            changeFragmentClickListener,
            addToDataBaseClickListener
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (isReadyLoader.invoke()) {
            VIEW_TYPE_LOADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOADER = 2
    }
}