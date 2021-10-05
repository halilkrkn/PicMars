package com.example.picmars.ui.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picmars.models.Camera
import com.example.picmars.models.PicMarsResponse
import com.example.picmars.repository.PicMarsRepository
import com.example.picmars.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    val picMarsRepository: PicMarsRepository,
): ViewModel() {

    val curiosityPic: MutableLiveData<Resource<PicMarsResponse>> = MutableLiveData()
    var curiosityPage = 1
    val sol = 1000

    fun getCuriosityRepo(camera: String) = viewModelScope.launch {
        curiosityPic.postValue(Resource.Loading())
        val response = picMarsRepository.getCuriosityRepo(sol,camera,curiosityPage)
        curiosityPic.postValue(handleCuriosityResponse(response))
    }

    private fun handleCuriosityResponse(response: Response<PicMarsResponse>) : Resource<PicMarsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}

