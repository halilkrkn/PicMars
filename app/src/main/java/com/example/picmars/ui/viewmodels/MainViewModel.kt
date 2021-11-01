package com.example.picmars.ui.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.picmars.db.PicMarsPhotoDao
import com.example.picmars.models.Photo
import com.example.picmars.models.PicMarsResponse
import com.example.picmars.repository.PicMarsRepository
import com.example.picmars.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    val picMarsRepository: PicMarsRepository,
): ViewModel() {

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

    // Database apideki veriler ekleniyor.
    fun savePhoto(photos: List<Photo>) = viewModelScope.launch {
        picMarsRepository.upsert(photos)
    }

    // Filtreleme İşlemleri için Repositoryden oluşturudpumuz search fonk getiriyoruz.
    val searchQuery = MutableStateFlow("")

    private val searchFlow = searchQuery.flatMapLatest {
       picMarsRepository.search(it)
    }

    val searchPhoto = searchFlow.asLiveData()
}






