package com.jalexy.meme.dashboard.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jalexy.meme.base.BindingFragment
import com.jalexy.meme.databinding.FragmentDashboardBinding

class DashboardFragment : BindingFragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

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

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}