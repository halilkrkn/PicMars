package com.example.picmars.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.adapters.PicMarsSpiritAdapters
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.MainViewModel
import com.example.picmars.util.Resource
import kotlinx.android.synthetic.main.spirit_fragment.*

class SpiritFragment: Fragment(R.layout.spirit_fragment) {
    lateinit var  viewModel: MainViewModel
    lateinit var  spiritAdapters: PicMarsSpiritAdapters
    val TAG = "SpiritFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.spiritPic.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { spiritResponse ->
                        spiritAdapters.differ.submitList(spiritResponse.photos)
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
        paginationProgressBarSpirit.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBarSpirit.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        spiritAdapters =  PicMarsSpiritAdapters()
        rvSpirit.apply {
            adapter = spiritAdapters
            layoutManager = LinearLayoutManager(activity)
        }
    }
}