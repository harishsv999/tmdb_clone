package com.harish.tmdb.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.harish.tmdb.databinding.FragmentDashBoardBinding
import com.harish.tmdb.presentation.adapters.GridLayoutAdapter
import com.harish.tmdb.presentation.utils.SpacesItemDecoration
import com.harish.tmdb.presentation.viewmodels.DashBoardViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFragment @Inject constructor() : Fragment() {

    private var _binding: FragmentDashBoardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashBoardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize()
        with(binding.dashBoardLayouts) {
            viewModel.pageState.observe(viewLifecycleOwner) {
                binding.progressIndicator.isVisible = it == null
                binding.dashBoardLayouts.isVisible = it == false
            }
            viewModel.movies.observe(viewLifecycleOwner) { movies ->
                movies?.let {
                    adapter = GridLayoutAdapter(it)
                    addItemDecoration(SpacesItemDecoration(20))
                    layoutManager = GridLayoutManager(requireContext(), 2)
                }
            }
        }
        Thread.sleep(10000)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}