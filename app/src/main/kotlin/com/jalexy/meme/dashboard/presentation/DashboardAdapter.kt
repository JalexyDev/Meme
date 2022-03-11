package com.jalexy.meme.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.jalexy.meme.R
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class DashboardAdapter @Inject constructor(callback: MemeItemDiffCallback) :
    ListAdapter<Meme, DashboardItemViewHolder>(callback) {

    lateinit var changeFragmentClickListener: ((Meme) -> Unit)
    lateinit var addToDataBaseClickListener: ((Meme, callback: (item: Meme) -> Unit) -> Meme)
    var isReadyLoader: () -> Boolean = {
        false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ITEM -> R.layout.item_meme_dashboard
            VIEW_TYPE_LOADER -> R.layout.item_meme_loading_dashboard
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return DashboardItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DashboardItemViewHolder, position: Int) {
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