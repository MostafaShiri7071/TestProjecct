package com.my.testproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.testproject.datamodel.response.LoginResponse
import com.my.testproject.repository.LoginRepository
import com.my.testproject.datamodel.base.Response
import com.my.testproject.datamodel.response.LeaderBoardResponse
import com.my.testproject.repository.MainRepository

/**
 * Created by Mostafa Shiri.
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel(){

    var leaderBoardLiveData = MutableLiveData<Response<LeaderBoardResponse>>()

    fun fetchLeaderBoard() {
        mainRepository.getLeaderBoardRepo()
        leaderBoardLiveData =mainRepository.leaderBoardLiveDataRepo
    }

    fun leaderBoard(): MutableLiveData<Response<LeaderBoardResponse>>? {
        return leaderBoardLiveData
    }

    override fun onCleared() {
        super.onCleared()
        mainRepository.disposable?.dispose()
    }
}