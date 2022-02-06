package com.jalexy.meme.dashboard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.jalexy.meme.R
import com.jalexy.meme.main.domain.models.Meme

class DashboardAdapter() : ListAdapter<Meme, DashboardItemViewHolder>
    (MemeItemDiffCallback()) {

    var changeFragmentClickListener: ((Meme) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_meme_info,
            parent,
            false
        )
        return DashboardItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DashboardItemViewHolder, position: Int) {

        val memeItem = getItem(position)

        viewHolder.itemView.setOnClickListener{
            changeFragmentClickListener?.invoke(memeItem)
        }

        Glide.with(viewHolder.itemView.context)
            .load(memeItem.image)
            .into(viewHolder.ivLogoMeme)

        viewHolder.tvTopText.text = memeItem.topText
        viewHolder.tvBottomText.text = memeItem.bottomText
        viewHolder.tvName.text = memeItem.name
        viewHolder.tvTags.text = memeItem.tags
    }
}