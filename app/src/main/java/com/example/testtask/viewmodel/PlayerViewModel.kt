package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Repository

class PlayerViewModel(
    private val liveDataForViewToObserve: MutableLiveData<Int> = MutableLiveData()
) : ViewModel() {
    fun getData(): LiveData<Int> {
        liveDataForViewToObserve.value = Repository.currentPosition
        return liveDataForViewToObserve
    }

    fun setData(currentPosition:Int){
        Repository.currentPosition = currentPosition
    }
}