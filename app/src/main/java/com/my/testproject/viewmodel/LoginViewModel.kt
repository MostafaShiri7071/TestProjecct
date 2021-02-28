package com.my.testproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.testproject.datamodel.response.LoginResponse
import com.my.testproject.repository.LoginRepository
import com.my.testproject.datamodel.base.Response

/**
 * Created by Mostafa Shiri.
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(){

    var loginLiveData = MutableLiveData<Response<LoginResponse>>()

    fun fetchLogin(userName:String,password:String) {
        loginRepository.loginRepo(userName,password)
        loginLiveData =loginRepository.loginLiveDataRepo
    }

    fun login(): MutableLiveData<Response<LoginResponse>>? {
        return loginLiveData
    }

    override fun onCleared() {
        super.onCleared()
        loginRepository.disposable?.dispose()
    }
}