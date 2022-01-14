package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.databinding.SpiritFragmentBinding
import com.example.picmars.ui.adapters.PicMarsSpiritAdapters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.spirit_fragment.*

@AndroidEntryPoint
class SpiritFragment: Fragment(R.layout.spirit_fragment) {

    lateinit var  spiritAdapters: PicMarsSpiritAdapters
    private var _binding: SpiritFragmentBinding ?= null
    private val binding get() = _binding!!

    val TAG = "SpiritFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = SpiritFragmentBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        spiritAdapters =  PicMarsSpiritAdapters()
        binding.recyclerViewSpirit.apply {
            adapter = spiritAdapters
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