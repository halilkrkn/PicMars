package com.example.picmars.ui.viewmodels.spirit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picmars.repository.PicMarsRepository

class SpiritViewModelProviderFactory(
    private val picMarsRepository: PicMarsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpiritViewModel(picMarsRepository) as T    }
}