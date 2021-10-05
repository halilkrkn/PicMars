package com.example.picmars.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picmars.repository.PicMarsRepository

class MainViewModelProviderFactory(
    private val picMarsRepository: PicMarsRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(picMarsRepository) as T    }

}