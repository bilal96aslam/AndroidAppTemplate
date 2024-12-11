package com.app.androidapptemplate.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {

    // LiveData for managing toast messages
    private val _toastMessage: MutableLiveData<String> = MutableLiveData()
    val toastMessage: LiveData<String> = _toastMessage

    // LiveData for managing loading state
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Show a toast message.
     */
    protected fun showToast(message: String) {
        _toastMessage.postValue(message)
    }

    /**
     * Show the loading state.
     */
    protected suspend fun showLoading() = withContext(Dispatchers.Main) {
        _isLoading.value = true
    }

    /**
     * Hide the loading state.
     */
    protected suspend fun hideLoading() = withContext(Dispatchers.Main) {
        _isLoading.value = false
    }
}
