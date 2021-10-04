package com.example.picmars.ui.viewmodels.curiosity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.picmars.repository.PicMarsRepository

class CuriosityViewModelProviderFactory(
    private val picMarsRepository: PicMarsRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CuriosityViewModel(picMarsRepository) as T    }
}