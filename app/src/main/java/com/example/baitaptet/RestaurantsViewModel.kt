package com.example.baitaptet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RestaurantsViewModel: ViewModel()  {
    private var _listOfImage: MutableLiveData<List<Image>> = MutableLiveData()
    val listOfIdol: LiveData<List<Image>>
        get() = _listOfImage

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun loadData() {
        _isLoading.postValue(true)

        viewModelScope.launch {
            delay(3000)
            val dataSet = DataStore.getDataSet()

            _isLoading.postValue(false)
            _listOfImage.postValue(dataSet)
        }
    }

    fun handleItemWhenClicked(item: Image) {
        /// TODO
    }

    fun handleItemWhenLongClicked(item: Image) {
        /// TODO
    }
}