package com.example.truongquockhanh.mymovieapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.truongquockhanh.mymovieapp.constant.NetworkState

abstract class BaseViewModel : ViewModel() {
    abstract val netWorkState: LiveData<NetworkState>
}