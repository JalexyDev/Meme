package com.jalexy.meme.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jalexy.meme.databinding.ItemMemeDashboardBinding
import com.jalexy.meme.main.domain.models.Meme
import javax.inject.Inject

class DashboardAdapter @Inject constructor(callback: MemeItemDiffCallback) :
    ListAdapter<Meme, DashboardItemViewHolder>(callback) {

    var changeFragmentClickListener: ((Meme) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val binding = ItemMemeDashboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DashboardItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DashboardItemViewHolder, position: Int) {

        val memeItem = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnClickListener {
            changeFragmentClickListener?.invoke(memeItem)
        }
        viewHolder.bind(memeItem)
    }
}