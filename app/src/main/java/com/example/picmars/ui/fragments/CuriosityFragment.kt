package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.picmars.R
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.curiosity.CuriosityViewModel

class CuriosityFragment: Fragment(R.layout.curiosity_fragment) {
    lateinit var  viewModelCuriosity: CuriosityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCuriosity = (activity as MainActivity).viewModelCuriosity
    }



}