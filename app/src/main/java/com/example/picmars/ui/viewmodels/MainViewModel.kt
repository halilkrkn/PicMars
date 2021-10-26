package com.example.picmars.ui.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picmars.models.Photo
import com.example.picmars.models.PicMarsResponse
import com.example.picmars.repository.PicMarsRepository
import com.example.picmars.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    val picMarsRepository: PicMarsRepository,
): ViewModel() {

//    val searchViewResponse: MutableLiveData<Camera> = MutableLiveData()
    val curiosityPic: MutableLiveData<Resource<PicMarsResponse>> = MutableLiveData()
    val opportunityPic: MutableLiveData<Resource<PicMarsResponse>> = MutableLiveData()
    val spiritPic: MutableLiveData<Resource<PicMarsResponse>> = MutableLiveData()


    //    var curiosityPage = 1
    val solCuriosity = 1000
    val solOpportunity = 10
    val solSpirit = 1
//    val camera: String = "fhaz"

init {
    getCuriosity()
    getOpportunity()
    getSpirit()

}

    private fun getCuriosity() = viewModelScope.launch {
        curiosityPic.postValue(Resource.Loading())
        val response = picMarsRepository.getCuriosityRepo(solCuriosity)
        curiosityPic.postValue(handleResponse(response))
    }

    private fun getOpportunity() = viewModelScope.launch {
        opportunityPic.postValue(Resource.Loading())
        val response = picMarsRepository.getOpportunityRepo(solOpportunity)
        opportunityPic.postValue(handleResponse(response))

    }
    private fun getSpirit() = viewModelScope.launch {
        spiritPic.postValue(Resource.Loading())
        val response = picMarsRepository.getSpiritRepo(solSpirit)
        spiritPic.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<PicMarsResponse>) : Resource<PicMarsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

<<<<<<< Updated upstream
    fun upsert(photo: Photo) = viewModelScope.launch {
        picMarsRepository.upsert(photo)
=======
    fun saveArticle(photos: List<Photo>) = viewModelScope.launch {
        picMarsRepository.upsert(photos)
>>>>>>> Stashed changes
    }

}

