package com.jalexy.meme.favorite.presentation

import android.app.ActionBar
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentFavoriteBinding
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import com.jalexy.meme.main.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : BindingFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::class) {

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                ScreenState.Content -> showLoading(false)
                is ScreenState.Error -> showError(it.text)
                ScreenState.Loading -> showLoading(true)
            }
        }

        setupRecyclerView()
        favoriteViewModel.getFavoriteList.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.rvMemeList.isVisible = !isLoading
        binding.loader.isVisible = isLoading
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        with(binding.rvMemeList) {
            adapter = favoriteAdapter
        }
        favoriteAdapter.changeFragmentClickListener = {
            launchInfoFragment(it)
        }
        favoriteAdapter.deleteToDataBaseClickListener = {
            favoriteViewModel.deleteMemeItemToDB(it)
        }
    }

    private fun launchInfoFragment(meme: Meme) {
        findNavController()
            .navigate(FavoriteFragmentDirections.actionNavigationFavoriteToNavigationHome(meme))
    }
}
