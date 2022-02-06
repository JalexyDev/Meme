package com.jalexy.meme.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentDashboardBinding
import com.jalexy.meme.main.domain.models.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BindingFragment() {

    private val dashboardViewModel by viewModels<DashboardViewModel>()
    private lateinit var memeAdapter: DashboardAdapter

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel.loadAllMeme(1)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        dashboardViewModel.screenState.observe(viewLifecycleOwner) {
//            when(it) {
//                ScreenState.Content -> showLoading(false)
//                is ScreenState.Error -> showError(it.text)
//                ScreenState.Loading -> showLoading(true)
//            }
//        }

        setupRecyclerView()
        dashboardViewModel.meme.observe(viewLifecycleOwner) {
            memeAdapter.submitList(it)
        }
        //todo доделать ресайклер
    }

//    private fun showLoading(isLoading: Boolean) {
//        binding.content.isVisible = !isLoading
//        binding.loader.isVisible = isLoading
//    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){

        with(binding.rvMemeList){
            memeAdapter = DashboardAdapter()
            adapter = memeAdapter
        }

        memeAdapter.changeFragmentClickListener = {
            Toast.makeText(context, "Переключение фрагмента ${it.id}", Toast.LENGTH_SHORT).show()

        }
    }


}