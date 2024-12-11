package com.app.androidapptemplate.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.androidapptemplate.base.BaseViewModel
import com.app.androidapptemplate.network.apiclient.ApiHelper
import com.app.androidapptemplate.network.apiclient.base.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val apiHelper: ApiHelper
) : BaseViewModel() {

    // state values
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        email.value = "eve.holt@reqres.in"
        password.value = "cityslicka"
    }

    fun login(success: () -> Unit) {
        viewModelScope.launch {
            showLoading()
            val response = apiHelper.login(email.value ?: "", password.value ?: "")
            withContext(Dispatchers.IO) {
                when (response) {
                    is ApiResponse.Success -> {
                        response.data.token?.let {
                            success()
                        }
                    }

                    is ApiResponse.Error -> {
                        showToast(response.error.message)
                    }
                }
            }
            hideLoading()
        }
    }
}