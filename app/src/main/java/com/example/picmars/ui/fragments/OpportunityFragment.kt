package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.databinding.OpportunityFragmentBinding
import com.example.picmars.ui.adapters.oppurtunity.OpportunityAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OpportunityFragment: Fragment(R.layout.opportunity_fragment) {

    lateinit var  opportunityAdapters: OpportunityAdapter
    var _binding: OpportunityFragmentBinding ?= null
    private val binding get() = _binding!!

    val TAG = "OpportunityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = OpportunityFragmentBinding.bind(view)


        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        opportunityAdapters = OpportunityAdapter()
            binding.recyclerViewOpportunity.apply {
            adapter = opportunityAdapters
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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}