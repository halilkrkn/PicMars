package com.example.picmars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picmars.R
import com.example.picmars.adapters.PicMarsCuriosityAdapters
import com.example.picmars.db.PicMarsPhotoDao
import com.example.picmars.db.PicMarsPhotoDb
import com.example.picmars.repository.PicMarsRepository
import com.example.picmars.ui.fragments.CuriosityFragment
import com.example.picmars.ui.viewmodels.MainViewModel
import com.example.picmars.ui.viewmodels.MainViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.curiosity_fragment.*

class MainActivity : AppCompatActivity() {

    //Olmadı  picMarViewModel Olarak tek bir viewModel oluştur.
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val picMarsRepository = PicMarsRepository(PicMarsPhotoDb(this))

        //Curiosity
        val mainViewModelProviderFactory = MainViewModelProviderFactory(picMarsRepository)

        viewModel = ViewModelProvider(this,mainViewModelProviderFactory).get(
            MainViewModel::class.java)

        // navigation components setup
        bottomNavigationView.setupWithNavController(marsNavHostFragment.findNavController())
    }

}