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
import com.my.testproject.datamodel.body.LeaderBoardBody
import com.my.testproject.datamodel.response.LeaderBoardResponse
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mostafa Shiri.
 */
class MainRepository(private val apiService: ApiService){

    var disposable: Disposable? = null

    val leaderBoardLiveDataRepo = MutableLiveData<Response<LeaderBoardResponse>>()

    fun getLeaderBoardRepo(){
        val body=LeaderBoardBody()
        apiService.getLeaderBoard(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ResponseBase<LeaderBoardResponse>> {
                override fun onSuccess(response: ResponseBase<LeaderBoardResponse>) {
                    leaderBoardLiveDataRepo.postValue(Response.success(response.data))
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    val a=e.message
                }
            })
    }

}