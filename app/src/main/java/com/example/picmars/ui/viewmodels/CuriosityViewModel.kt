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
class CuriosityViewModel @Inject constructor(
    private val picMarsRepository: PicMarsRepository,
) : ViewModel() {

    private val currentQueryCamera = MutableLiveData("")
    val getCuriosityPicMars = currentQueryCamera.switchMap { queryCamera ->
        picMarsRepository.getCuriositySearchResult(queryCamera).cachedIn(viewModelScope)
    }

    fun allCuriosityPicMars() = picMarsRepository.allCuriosityPicMars().cachedIn(viewModelScope)


    fun searchCamera(queryCamera: String) {
        currentQueryCamera.value = queryCamera
    }

}