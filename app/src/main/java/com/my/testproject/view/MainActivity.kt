package com.my.testproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.my.testproject.R
import com.my.testproject.core.toast
import androidx.lifecycle.Observer
import com.my.testproject.viewmodel.LoginViewModel
import com.my.testproject.viewmodel.MainViewModel
import com.s.testproject.datamodel.base.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    private val leaderBoardAdapter: LeaderBoardAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRv()
        initObserve()
    }

    private fun initObserve() {
        mainViewModel.fetchLeaderBoard()
        mainViewModel.leaderBoard()?.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    leaderBoardAdapter.clear()
                    it.data?.leaderboard?.let { it1 -> leaderBoardAdapter.addItems(it1) }
                }
                Status.LOADING -> {
                    //Show ProgressBar
                }
                Status.ERROR -> {
                    toast("Error")
                    //Handle Error
                }
            }
        })
    }

    private fun initRv() {
        rv_leaderBoard.adapter = leaderBoardAdapter
        leaderBoardAdapter.adapterOnClick = {
            toast(it.displayName)
        }
    }
}