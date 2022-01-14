
package com.example.picmars.ui.viewmodels
import androidx.lifecycle.ViewModel
import com.example.picmars.data.repository.PicMarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class SpiritViewModel @Inject constructor(
    val picMarsRepository: PicMarsRepository,
): ViewModel() {

}






