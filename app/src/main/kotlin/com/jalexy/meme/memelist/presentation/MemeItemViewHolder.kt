package com.jalexy.meme.memelist.presentation

import android.graphics.Color
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.jalexy.meme.databinding.ItemMemeBinding
import com.jalexy.meme.databinding.ItemMemeLoadingBinding
import com.jalexy.meme.main.domain.models.Meme

class MemeItemViewHolder(private val binding: ViewBinding) :

    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        meme: Meme,
        changeFragmentClickListener: ((Meme) -> Unit),
        addToDataBaseClickListener: ((Meme, callback: (item: Meme) -> Unit) -> Meme),
    ) {
        if(binding is ItemMemeBinding) {
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
        }else if (binding is ItemMemeLoadingBinding){
            binding.loader.isVisible = true
        }
    }

    private fun setButtonColor(meme: Meme) {
        if(binding is ItemMemeBinding) {
            if (!meme.isFavorite) {
                binding.buttonAddToDatabase.setBackgroundColor(Color.LTGRAY)
            } else {
                binding.buttonAddToDatabase.setBackgroundColor(Color.BLACK)
            }
        }
    }
}