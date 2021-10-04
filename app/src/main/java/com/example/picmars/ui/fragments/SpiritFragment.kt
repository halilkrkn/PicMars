package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.picmars.R
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.curiosity.CuriosityViewModel
import com.example.picmars.ui.viewmodels.spirit.SpiritViewModel

class SpiritFragment: Fragment(R.layout.spirit_fragment) {


    lateinit var viewModelSpirit: SpiritViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelSpirit = (activity as MainActivity).viewModelSpirit
    }

}