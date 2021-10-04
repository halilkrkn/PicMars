package com.example.picmars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.picmars.R
import com.example.picmars.db.PicMarsPhotoDb
import com.example.picmars.repository.PicMarsRepository
import com.example.picmars.ui.viewmodels.curiosity.CuriosityViewModel
import com.example.picmars.ui.viewmodels.curiosity.CuriosityViewModelProviderFactory
import com.example.picmars.ui.viewmodels.opportunity.OpportunityViewModel
import com.example.picmars.ui.viewmodels.opportunity.OpportunityViewModelProviderFactory
import com.example.picmars.ui.viewmodels.spirit.SpiritViewModel
import com.example.picmars.ui.viewmodels.spirit.SpiritViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Olmadı  picMarViewModel Olarak tek bir viewModel oluştur.



    lateinit var viewModelCuriosity: CuriosityViewModel
    lateinit var viewModelOpportunity: OpportunityViewModel
    lateinit var viewModelSpirit: SpiritViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val picMarsRepository = PicMarsRepository(PicMarsPhotoDb(this))

        //Curiosity
        val viewModelProviderFactoryCuriosity = CuriosityViewModelProviderFactory(picMarsRepository)
        viewModelCuriosity = ViewModelProvider(this,viewModelProviderFactoryCuriosity).get(
            CuriosityViewModel::class.java)

        //Opportunity
        val viewModelProviderFactoryOpportunity = OpportunityViewModelProviderFactory(picMarsRepository)

        viewModelOpportunity = ViewModelProvider(this,viewModelProviderFactoryOpportunity).get(
            OpportunityViewModel::class.java)

        //Spirit
        val viewModelProviderFactorySpirit = SpiritViewModelProviderFactory(picMarsRepository)
        viewModelSpirit = ViewModelProvider(this,viewModelProviderFactorySpirit).get(
            SpiritViewModel::class.java)


        // navigation components setup
        bottomNavigationView.setupWithNavController(marsNavHostFragment.findNavController())

    }


}