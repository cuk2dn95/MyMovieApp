package com.example.truongquockhanh.mymovieapp.common

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
    return SingleTransformer {
        it.subscribeOn(Schedulers.io())
        it.observeOn(AndroidSchedulers.mainThread())
    }
}
