package com.example.picmars.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.adapters.PicMarsCuriosityAdapters
import com.example.picmars.models.Photo
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.MainViewModel
import com.example.picmars.util.Resource
import com.example.picmars.util.onQueryTextChanged
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.curiosity_fragment.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class CuriosityFragment(): Fragment(R.layout.curiosity_fragment){

    lateinit var  viewModel: MainViewModel
    lateinit var  curiosityAdapters: PicMarsCuriosityAdapters
    val TAG = "CuriosityFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        // Oluşturduğumuz recyclerView adapterinin setupı yapıldı.
        setupRecyclerView()

        viewModel.curiosityPic.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { curiosityResponse ->
                        curiosityAdapters.differ.submitList(curiosityResponse.photos) // Apideki tüm verileri fragmentte göstermek için
                        viewModel.savePhoto(curiosityResponse.photos) // database apideki verileri eklenmek için viewModelden fomk çağırıldı.
                        viewModel.searchPhoto.observe(viewLifecycleOwner){ // Filtereleme işlemi için viewModelden oluşturulan fonk. çağrıldı.
                            curiosityAdapters.differ.submitList(it)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An Error Occured(BİR HATA OLUŞTU): $message")
                        Toast.makeText(context, "Bir Hata Oluştu.", Toast.LENGTH_SHORT).show();
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        paginationProgressBarCuriosity.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        paginationProgressBarCuriosity.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        curiosityAdapters =  PicMarsCuriosityAdapters()
        rvCuriosity.apply {
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

        // Filrelemedeki anahtar kelimeyi yazıp değişikliği harekete geçiren yapı.
       searchView.onQueryTextChanged {
           viewModel.searchQuery.value = it
       }
    }




}