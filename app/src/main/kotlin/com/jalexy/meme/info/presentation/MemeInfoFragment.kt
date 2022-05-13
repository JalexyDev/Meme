package com.jalexy.meme.info.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jalexy.meme.R
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentMemeInfoBinding
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemeInfoFragment : BindingFragment<FragmentMemeInfoBinding>(FragmentMemeInfoBinding::class) {

    private val homeViewModel by viewModels<MemeInfoViewModel>()

    private val args by navArgs<MemeInfoFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.loadMemeInfo(args.meme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                ScreenState.Content -> showLoading(false)
                is ScreenState.Error -> showError(it.text)
                ScreenState.Loading -> showLoading(true)
            }
        }

        homeViewModel.memeInfo.observe(viewLifecycleOwner) {
            Glide.with(this)
                .load(it.imageUrl)
                .error(R.drawable.notfound)
                .into(binding.image)

            binding.topText.text = it.topText
            binding.bottomText.text = it.bottomText
            binding.titleText.text = it.name
            binding.descriptionText.text = it.details
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.content.isVisible = !isLoading
        binding.loader.isVisible = isLoading
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}