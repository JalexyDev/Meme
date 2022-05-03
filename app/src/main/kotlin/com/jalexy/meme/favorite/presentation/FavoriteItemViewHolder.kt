package com.jalexy.meme.favorite.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jalexy.meme.R
import com.jalexy.meme.databinding.ItemMemeFavotiteBinding
import com.jalexy.meme.main.domain.models.Meme

class FavoriteItemViewHolder(private val binding: ItemMemeFavotiteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        meme: Meme,
        changeFragmentClickListener: ((Meme) -> Unit)?,
        deleteToDataBaseClickListener: ((Meme) -> Unit)?

    ) {
        if (meme.image == "demoImage"){
            binding.ivLogoMeme.setImageResource(R.drawable.ricardo)
        }else if (meme.image.isEmpty()){
            binding.ivLogoMeme.setImageResource(R.drawable.notfound)
        }else{
            Glide.with(itemView.context)
                .load(meme.image)
                .error(R.drawable.notfound)
                .into(binding.ivLogoMeme)
        }
        binding.tvTopText.text = meme.topText
        binding.tvBottomText.text = meme.bottomText
        binding.tvTitleText.text = meme.name
        binding.tvDescriptionText.text = meme.tags
        binding.root.setOnClickListener {
            changeFragmentClickListener?.invoke(meme)
        }
        binding.buttonDeleteToDatabase.setOnClickListener {
            deleteToDataBaseClickListener?.invoke(meme)
        }
    }
}