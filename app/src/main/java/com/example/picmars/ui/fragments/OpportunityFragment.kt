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
import com.example.picmars.databinding.OpportunityFragmentBinding
import com.example.picmars.ui.adapters.curiosity.CuriosityLoadStateAdapter
import com.example.picmars.ui.adapters.curiosity.OpportunityAdapter
import com.example.picmars.ui.adapters.oppurtunity.OpportunityLoadStateAdapter
import com.example.picmars.ui.viewmodels.OpportunityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpportunityFragment : Fragment(R.layout.opportunity_fragment) {

    lateinit var opportunityAdapter: OpportunityAdapter
    private val viewModel by viewModels<OpportunityViewModel>()
    var _binding: OpportunityFragmentBinding? = null
    private val binding get() = _binding!!

    val TAG = "OpportunityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = OpportunityFragmentBinding.bind(view)

        setupRecyclerView()


        binding.buttonRetryOpportunity.setOnClickListener {
            opportunityAdapter.retry()
        }

        viewModel.getOpportunityPicMars.observe(viewLifecycleOwner) {
            opportunityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.allOpportunityPicMars().observe(viewLifecycleOwner) {
            opportunityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        opportunityAdapter.addLoadStateListener { loadState ->
            binding.apply {
                recyclerViewOpportunity.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBarOpportunity.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetryOpportunity.isVisible = loadState.source.refresh is LoadState.Error
                textViewErrorOpportunity.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    opportunityAdapter.itemCount < 1
                ) {
                    recyclerViewOpportunity.isVisible = false
                    textViewEmptyOpportunity.isVisible = true
                } else {
                    textViewEmptyOpportunity.isVisible = false
                }
            }
        }

        setHasOptionsMenu(true)
    }

    private fun setupRecyclerView() {
        opportunityAdapter = OpportunityAdapter()
        binding.recyclerViewOpportunity.apply {
            adapter = opportunityAdapter.withLoadStateHeaderAndFooter(
                header = OpportunityLoadStateAdapter { opportunityAdapter.retry() },
                footer = OpportunityLoadStateAdapter { opportunityAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(activity)
        }
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
                binding.recyclerViewOpportunity.scrollToPosition(0)
                viewModel.searchCamera(newText)
                return true
            }

        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.allOpportunityPicMars().observe(viewLifecycleOwner) {
            opportunityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}