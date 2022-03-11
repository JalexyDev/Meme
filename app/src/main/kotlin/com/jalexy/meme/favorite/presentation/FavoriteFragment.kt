package com.jalexy.meme.favorite.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jalexy.meme.databinding.FragmentFavoriteBinding
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            .navigate(FavoriteFragmentDirections.actionNavigationNotificationsToNavigationHome(meme))
    }
}
