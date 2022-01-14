package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.databinding.CuriosityFragmentBinding
import com.example.picmars.ui.adapters.PicMarsCuriosityAdapters
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.curiosity_fragment.*

@AndroidEntryPoint
class CuriosityFragment(): Fragment(R.layout.curiosity_fragment){

    lateinit var  curiosityAdapters: PicMarsCuriosityAdapters
    private var _binding: CuriosityFragmentBinding? = null
    private val binding get() = _binding!!
    val TAG = "CuriosityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = CuriosityFragmentBinding.bind(view)


        setupRecyclerView()



    }


    private fun setupRecyclerView(){
        curiosityAdapters =  PicMarsCuriosityAdapters()
       binding.recyclerViewCuriosity.apply {
            adapter = curiosityAdapters
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}