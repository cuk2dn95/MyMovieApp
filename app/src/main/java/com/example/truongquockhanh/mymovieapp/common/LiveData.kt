package com.example.truongquockhanh.mymovieapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T> createMergeLiveData(sourceOne: LiveData<T>, sourceTwo: LiveData<T>): LiveData<T> {
    return MergeLiveData(sourceOne, sourceTwo)
}

class MergeLiveData<T>(sourceOne: LiveData<T>, sourceTwo: LiveData<T>) : MediatorLiveData<T>() {
    init {
        addSource(sourceOne) { postValue(it) }
        addSource(sourceTwo) { postValue(it) }
    }
}