package com.jalexy.meme.memelist.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentDashboardBinding
import com.jalexy.meme.main.domain.models.Meme
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MemeListFragment :
    BindingFragment<FragmentDashboardBinding>(FragmentDashboardBinding::class) {

    private val dashboardViewModel by viewModels<MemeListViewModel>()

    private var isLoadingItem = true

    @Inject
    lateinit var memeAdapter: MemeListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel.screenState.observe(viewLifecycleOwner) {
            when (it) {
                ScreenState.Content -> showLoading(false)
                is ScreenState.Error -> showError(it.text)
                ScreenState.Loading -> showLoading(true)
            }
        }
        setupRecyclerView()
        dashboardViewModel.meme.observe(viewLifecycleOwner) {
            memeAdapter.submitList(it)
        }

        binding.rvMemeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPos = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPos == totalItemCount - 1) {
                    isLoadingItem = false
                    memeAdapter.createLoader()
                    dashboardViewModel.loadingNextPage()
                }
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoadingItem) {
            binding.rvMemeList.isVisible = !isLoading
            binding.loader.isVisible = isLoading
        }else{
            binding.rvMemeList.isVisible = true
            binding.loader.isVisible = false
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        with(binding.rvMemeList) {
            adapter = memeAdapter
        }
        memeAdapter.changeFragmentClickListener = {
            launchInfoFragment(it)
        }
        memeAdapter.addToDataBaseClickListener = { item, callback ->
            dashboardViewModel.addMemeItemToDB(item, callback)
        }
    }

    private fun launchInfoFragment(meme: Meme) {
        findNavController()
            .navigate(MemeListFragmentDirections
                .actionNavigationMemeListToNavigationInfo(meme))
    }
}