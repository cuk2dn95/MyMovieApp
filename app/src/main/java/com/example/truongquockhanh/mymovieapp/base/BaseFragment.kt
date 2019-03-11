package com.example.truongquockhanh.mymovieapp.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.truongquockhanh.mymovieapp.R
import com.example.truongquockhanh.mymovieapp.constant.NetworkState
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment : Fragment() {

    abstract val netWorkState: LiveData<NetworkState>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        netWorkState.observe(this, androidx.lifecycle.Observer { networkState ->
            when (networkState) {
                NetworkState.ERROR -> {
                    hideLoading()
                    Snackbar.make(view, R.string.error_message_default, Snackbar.LENGTH_SHORT)
                }
                NetworkState.LOADING -> showLoading()
                NetworkState.SUCCESS -> hideLoading()
            }
        })
    }

    abstract fun showLoading()

    abstract fun hideLoading()
}