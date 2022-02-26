package com.example.picmars.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.picmars.data.api.PicMarsResponse
import com.example.picmars.data.repository.PicMarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CuriosityViewModel @Inject constructor(
    private val picMarsRepository: PicMarsRepository,
): ViewModel() {

    private val currentQueryCamera = MutableLiveData("")
    val getAllCuriosityPicMars = currentQueryCamera.switchMap { queryCamera ->
        picMarsRepository.getPicMarsCuriositySearchResult(queryCamera).cachedIn(viewModelScope)
    }

    fun allCuriosityPicMars() = picMarsRepository.allCuriosityPicMars().cachedIn(viewModelScope)


    fun searchCamera(queryCamera: String){
        currentQueryCamera.value = queryCamera
    }

}