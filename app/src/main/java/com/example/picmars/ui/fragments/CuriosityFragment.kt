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
import com.example.picmars.databinding.CuriosityFragmentBinding
import com.example.picmars.ui.adapters.curiosity.CuriosityAdapter
import com.example.picmars.ui.adapters.curiosity.CuriosityLoadStateAdapter
import com.example.picmars.ui.viewmodels.CuriosityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CuriosityFragment() : Fragment(R.layout.curiosity_fragment) {

    private val viewModel by viewModels<CuriosityViewModel>()
    lateinit var curiosityAdapter: CuriosityAdapter
    private var _binding: CuriosityFragmentBinding? = null
    private val binding get() = _binding!!
    val TAG = "CuriosityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = CuriosityFragmentBinding.bind(view)


        setupRecyclerView()

        // Burada ise CuriosityFragment içerisindeki hata mesajı içerisnde ki Retry BUtonuna tekrardan verileri çekmesi için butonu aktifleştirdik.
        binding.buttonRetryCuriosity.setOnClickListener {
            curiosityAdapter.retry()
        }

        viewModel.getAllCuriosityPicMars.observe(viewLifecycleOwner){
            curiosityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        viewModel.allCuriosityPicMars().observe(viewLifecycleOwner){
            curiosityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        curiosityAdapter.addLoadStateListener { loadState ->
            binding.apply {
                recyclerViewCuriosity.isVisible = loadState.source.refresh is LoadState.NotLoading
                progressBarCuriosity.isVisible = loadState.source.refresh is LoadState.Loading
                buttonRetryCuriosity.isVisible = loadState.source.refresh is LoadState.Error
                textViewErrorCuriosity.isVisible = loadState.source.refresh is LoadState.Error
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    curiosityAdapter.itemCount < 1
                ) {
                    recyclerViewCuriosity.isVisible = false
                    textViewEmptyCuriosity.isVisible = true
                } else {

                    textViewEmptyCuriosity.isVisible = false
                }
            }
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
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                binding.recyclerViewCuriosity.scrollToPosition(0)
                viewModel.searchCamera(newText)
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.allCuriosityPicMars().observe(viewLifecycleOwner){
            curiosityAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }


    private fun setupRecyclerView() {
        curiosityAdapter = CuriosityAdapter()
        binding.recyclerViewCuriosity.apply {
            setHasFixedSize(true)
            adapter = curiosityAdapter.withLoadStateHeaderAndFooter(
                header = CuriosityLoadStateAdapter { curiosityAdapter.retry() },
                footer = CuriosityLoadStateAdapter { curiosityAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(activity)
        }
    }
}