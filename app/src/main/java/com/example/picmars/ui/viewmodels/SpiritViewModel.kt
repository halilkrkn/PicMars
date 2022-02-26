package com.example.picmars.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.picmars.data.repository.PicMarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpiritViewModel @Inject constructor(
    val picMarsRepository: PicMarsRepository,
) : ViewModel() {

    private val currentQueryCamera = MutableLiveData("")
    val getSpiritPicMars = currentQueryCamera.switchMap { queryCamera ->
        picMarsRepository.getSpiritSearchResult(queryCamera).cachedIn(viewModelScope)
    }

    fun allSpiritPicMars() = picMarsRepository.allSpiritPicMars().cachedIn(viewModelScope)


    fun searchCamera(queryCamera: String) {
        currentQueryCamera.value = queryCamera
    }
}






