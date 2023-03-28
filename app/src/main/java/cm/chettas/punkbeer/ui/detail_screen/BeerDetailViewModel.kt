package cm.chettas.punkbeer.ui.detail_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.repository.MainRepository
import cm.chettas.punkbeer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _beer = MutableLiveData<BeerEntity>()
    val beer : LiveData<BeerEntity>
        get() = _beer

    fun getBeer(id: Int){
        viewModelScope.launch {
            _beer.postValue(repository.getBeerById(id))
        }
    }

}