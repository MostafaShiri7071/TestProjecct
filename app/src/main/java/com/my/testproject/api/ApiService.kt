package com.my.testproject.api


import com.my.testproject.core.Utils
import com.my.testproject.datamodel.base.ResponseBase
import com.my.testproject.datamodel.body.LeaderBoardBody
import com.my.testproject.datamodel.body.LoginBody
import com.my.testproject.datamodel.response.LeaderBoardResponse
import com.my.testproject.datamodel.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    companion object {
        const val BASE_URL = "https://3766a.playfabapi.com/Client/"
    }

    @POST("LoginWithPlayFab")
    fun loginApi(@Body loginBody: LoginBody): Single<ResponseBase<LoginResponse>>

    @POST("GetLeaderboard")
    fun getLeaderBoard(@Body leaderBoardBody: LeaderBoardBody): Single<ResponseBase<LeaderBoardResponse>>

}