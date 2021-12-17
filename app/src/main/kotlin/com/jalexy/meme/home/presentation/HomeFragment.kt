package com.jalexy.meme.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jalexy.meme.base.presentation.BindingFragment
import com.jalexy.meme.databinding.FragmentHomeBinding

class HomeFragment : BindingFragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.text.observe(viewLifecycleOwner) {
            binding.textHome.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}