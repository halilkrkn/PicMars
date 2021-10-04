package com.example.picmars.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.picmars.R
import com.example.picmars.ui.MainActivity
import com.example.picmars.ui.viewmodels.curiosity.CuriosityViewModel
import com.example.picmars.ui.viewmodels.opportunity.OpportunityViewModel

class OpportunityFragment: Fragment(R.layout.opportunity_fragment) {

    lateinit var viewModelOpportunity: OpportunityViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelOpportunity = (activity as MainActivity).viewModelOpportunity
    }


}