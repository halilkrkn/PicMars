package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.databinding.SpiritFragmentBinding
import com.example.picmars.ui.adapters.curiosity.CuriosityLoadStateAdapter
import com.example.picmars.ui.adapters.spirit.SpiritAdapter
import com.example.picmars.ui.adapters.spirit.SpiritLoadStateAdapter
import com.example.picmars.ui.viewmodels.CuriosityViewModel
import com.example.picmars.ui.viewmodels.SpiritViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpiritFragment : Fragment(R.layout.spirit_fragment) {

    lateinit var spiritAdapter: SpiritAdapter
    private val viewModel by viewModels<SpiritViewModel>()
    private var _binding: SpiritFragmentBinding? = null
    private val binding get() = _binding!!

    val TAG = "SpiritFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SpiritFragmentBinding.bind(view)
        setupRecyclerView()
        binding.buttonRetrySpirit.setOnClickListener {
            spiritAdapter.retry()
        }

        viewModel.getSpiritPicMars.observe(viewLifecycleOwner) {
            spiritAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.allSpiritPicMars().observe(viewLifecycleOwner) {
            spiritAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        spiritAdapter.addLoadStateListener { loadState ->
            binding.apply {
                recyclerViewSpirit.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBarSpirit.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetrySpirit.isVisible = loadState.source.refresh is LoadState.Error
                textViewErrorSpirit.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    spiritAdapter.itemCount < 1
                ) {
                    recyclerViewSpirit.isVisible = false
                    textViewEmptySpirit.isVisible = true
                } else {

                    textViewEmptySpirit.isVisible = false
                }
            }
        }


    }

    private fun setupRecyclerView() {
        spiritAdapter = SpiritAdapter()
        binding.recyclerViewSpirit.apply {
            adapter = spiritAdapter.withLoadStateHeaderAndFooter(
                header = SpiritLoadStateAdapter { spiritAdapter.retry() },
                footer = CuriosityLoadStateAdapter { spiritAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_mars_pic_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Please Write The Camera Name"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                binding.recyclerViewSpirit.scrollToPosition(0)
                viewModel.searchCamera(newText)
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.allSpiritPicMars().observe(viewLifecycleOwner) {
            spiritAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}