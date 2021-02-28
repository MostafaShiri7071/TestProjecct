package com.my.testproject.di

import com.my.testproject.view.LeaderBoardAdapter
import org.koin.dsl.module

/**
 * Created by Mostafa Shiri.
 */

val adapterModule = module {
    factory{ LeaderBoardAdapter()}
}