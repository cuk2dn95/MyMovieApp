package com.example.truongquockhanh.mymovieapp.common

import androidx.lifecycle.LiveData
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.example.truongquockhanh.mymovieapp.data.paging.PagingRequestHelper


fun PagingRequestHelper.createStatusLiveData(): LiveData<NetworkState> {
    val liveData = androidx.lifecycle.MutableLiveData<NetworkState>()
    addListener { report ->
        when {
            report.hasRunning() -> liveData.postValue(NetworkState.LOADING)
            report.hasError() -> liveData.postValue(NetworkState.ERROR)
            else -> liveData.postValue(NetworkState.SUCCESS)
        }
    }
    return liveData
}
