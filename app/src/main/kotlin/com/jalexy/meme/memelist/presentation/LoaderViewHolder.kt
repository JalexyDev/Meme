package com.jalexy.meme.memelist.presentation

import android.graphics.Color
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jalexy.meme.databinding.ItemMemeBinding
import com.jalexy.meme.databinding.ItemMemeLoadingBinding
import com.jalexy.meme.main.domain.models.Meme

class LoaderViewHolder(private val binding: ItemMemeLoadingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.loader.isVisible = true
    }

//    private fun setButtonColor(meme: Meme) {
//        if (!meme.isFavorite) {
//            binding.buttonAddToDatabase.setBackgroundColor(Color.LTGRAY)
//        } else {
//            binding.buttonAddToDatabase.setBackgroundColor(Color.BLACK)
//        }
//    }
}