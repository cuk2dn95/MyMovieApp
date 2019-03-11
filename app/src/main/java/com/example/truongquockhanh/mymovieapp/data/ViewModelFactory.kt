package com.example.truongquockhanh.mymovieapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val creators: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator: Provider<ViewModel> =
            creators[modelClass] ?: creators.entries.firstOrNull { it.key.isAssignableFrom(modelClass) }?.value
            ?: throw IllegalArgumentException("the class not provide")
        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw  e
        }
    }
}