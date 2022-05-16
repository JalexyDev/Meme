package com.jalexy.meme.memelist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jalexy.meme.databinding.ItemMemeBinding
import com.jalexy.meme.databinding.ItemMemeLoadingBinding
import com.jalexy.meme.main.domain.models.Meme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MemeListAdapter @Inject constructor(
    callback: MemeItemDiffCallback,
) :
    ListAdapter<ListItem, RecyclerView.ViewHolder>(callback) {

    lateinit var changeFragmentClickListener: ((Meme) -> Unit)
    lateinit var addToDataBaseClickListener: ((Meme, callback: (item: Meme) -> Unit) -> Meme)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_ITEM ->
                MemeItemViewHolder(ItemMemeBinding.inflate(inflater, parent, false))
            VIEW_TYPE_LOADER ->
                LoaderViewHolder(ItemMemeLoadingBinding.inflate(inflater, parent, false))
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (val memeItem = getItem(position)) {
            is Meme -> (viewHolder as MemeItemViewHolder).bind(
                memeItem,
                changeFragmentClickListener,
                addToDataBaseClickListener
            )
            is Loader -> (viewHolder as LoaderViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val memeItem = getItem(position)
        return if (memeItem.getItemId() == VIEW_TYPE_LOADER) {
            VIEW_TYPE_LOADER
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun createLoader() {
        if (currentList[currentList.lastIndex-1].getItemId() != VIEW_TYPE_LOADER) {
            val listMut: MutableList<ListItem> = currentList.toMutableList()
            listMut.add(Loader)
            submitList(listMut)
        }else{
            return
        }
    }

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOADER = -1
    }
}