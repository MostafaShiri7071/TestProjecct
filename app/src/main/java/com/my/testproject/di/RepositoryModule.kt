package com.my.testproject.di

import com.my.testproject.repository.LoginRepository
import com.my.testproject.repository.MainRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    factory{ LoginRepository(get(),androidApplication()) }
    factory{ MainRepository(get()) }
}