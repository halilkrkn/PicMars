package com.example.picmars.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picmars.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PicMarsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picmars)

    }

}