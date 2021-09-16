package com.example.testtask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtask.model.Repository

class StagesViewModel(
    private val liveDataForViewToObserve: MutableLiveData<List<String>> = MutableLiveData()
) : ViewModel() {
    fun getData(): LiveData<List<String>> {
        liveDataForViewToObserve.value = Repository.stages
        return liveDataForViewToObserve
    }
}