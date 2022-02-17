package com.jalexy.meme.info.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentMemeInfoBinding
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemeInfoFragment : BindingFragment() {

    private val homeViewModel by viewModels<MemeInfoViewModel>()

    private val args by navArgs<MemeInfoFragmentArgs>()
    private var _binding: FragmentMemeInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo сделать выбор id из аргументов или еще как
        homeViewModel.loadMeme(args.meme.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMemeInfoBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}