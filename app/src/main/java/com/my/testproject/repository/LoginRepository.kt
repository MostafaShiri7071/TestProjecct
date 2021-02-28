package com.my.testproject.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.my.testproject.api.ApiService
import com.my.testproject.core.Constant
import com.my.testproject.core.sharedPreferencesHelper
import com.my.testproject.datamodel.base.ResponseBase
import com.my.testproject.datamodel.body.LoginBody
import com.my.testproject.datamodel.response.LoginResponse
import com.my.testproject.datamodel.base.Response
import com.my.testproject.datamodel.base.Response.Companion.loading
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mostafa Shiri.
 */
class LoginRepository(private val apiService: ApiService,private val application: Application){

    var disposable: Disposable? = null

    val loginLiveDataRepo = MutableLiveData<Response<LoginResponse>>()

    fun loginRepo(userName:String,password:String){
        loginLiveDataRepo.postValue(loading(null))
        val body=LoginBody(username = userName,password = password)
        apiService.loginApi(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseBase<LoginResponse>> {
                override fun onSuccess(response: ResponseBase<LoginResponse>) {
                    response.data?.sessionTicket?.let {
                        application.sharedPreferencesHelper().setString(Constant.TOKEN,
                            it
                        )
                    }
                    loginLiveDataRepo.postValue(Response.success(response.data))
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    val a=e.message
                    //loginLiveDataRepo.postValue(error(""))
                }
            })
    }

}