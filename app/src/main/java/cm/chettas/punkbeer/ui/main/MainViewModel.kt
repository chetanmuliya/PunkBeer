package cm.chettas.punkbeer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cm.chettas.punkbeer.data.local.BeerEntity
import cm.chettas.punkbeer.repository.MainRepository
import cm.chettas.punkbeer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {

    private val _beers = MutableLiveData<Resource<List<BeerEntity>>>()
    val beers : LiveData<Resource<List<BeerEntity>>>
        get() = _beers

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun getBeers(){
        viewModelScope.launch {
            when(val result = repository.getBeers()){
                is Resource.Error -> _beers.postValue(Resource.Error(result.message ?: "Error"))
                is Resource.Loading ->  _beers.postValue(Resource.Loading())
                is Resource.Success ->  {
                    _beers.postValue(Resource.Success(result.data))
                    _isLoading.value = true
                }
            }
        }
    }

    fun searchBeer(query: String) {
        viewModelScope.launch {
            val result = repository.getBeerByName(query)
            _beers.postValue(Resource.Success(result))
        }
    }
}