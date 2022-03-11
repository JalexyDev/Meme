package com.jalexy.meme.dashboard.presentation

import android.graphics.Color
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jalexy.meme.databinding.ItemMemeDashboardBinding
import com.jalexy.meme.databinding.ItemMemeLoadingDashboardBinding
import com.jalexy.meme.main.domain.models.Meme

class DashboardItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        meme: Meme,
        changeFragmentClickListener: ((Meme) -> Unit),
        addToDataBaseClickListener: ((Meme, callback: (item: Meme) -> Unit) -> Meme),
    ) {
        when (binding) {
            is ItemMemeLoadingDashboardBinding -> {
                binding.loader.isVisible = true
            }
            is ItemMemeDashboardBinding -> {
                Glide.with(itemView.context)
                    .load(meme.image)
                    .into(binding.ivLogoMeme)
                binding.tvTopText.text = meme.topText
                binding.tvBottomText.text = meme.bottomText
                binding.tvTitleText.text = meme.name
                binding.tvDescriptionText.text = meme.tags
                setButtonColor(meme)
                binding.root.setOnClickListener {
                    changeFragmentClickListener.invoke(meme)
                }
                binding.buttonAddToDatabase.setOnClickListener {
                    addToDataBaseClickListener.invoke(meme) { meme -> setButtonColor(meme) }
                }
            }
        }
    }

    private fun setButtonColor(meme: Meme) {
        if (binding is ItemMemeDashboardBinding) {
            if (!meme.isFavorite) {
                binding.buttonAddToDatabase.setBackgroundColor(Color.LTGRAY)
            } else {
                binding.buttonAddToDatabase.setBackgroundColor(Color.BLACK)
            }
        } else {
            return
        }
    }
}