package com.example.picmars.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.adapters.PicMarsOpportunityAdapters
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.MainViewModel
import com.example.picmars.util.Resource
import kotlinx.android.synthetic.main.opportunity_fragment.*

class OpportunityFragment: Fragment(R.layout.opportunity_fragment) {

    lateinit var  viewModel: MainViewModel
    lateinit var  opportunityAdapters: PicMarsOpportunityAdapters
    val TAG = "OpportunityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.opportunityPic.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { opportunityResponse ->
                        opportunityAdapters.differ.submitList(opportunityResponse.photos)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An Error Occured(BİR HATA OLUŞTU): $message")

                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar() {
        paginationProgressBarOpportunity.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBarOpportunity.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        opportunityAdapters = PicMarsOpportunityAdapters()
        rvOpportunity.apply {
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }

}