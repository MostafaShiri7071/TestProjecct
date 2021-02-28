package com.my.testproject.di

import com.my.testproject.viewmodel.LoginViewModel
import com.my.testproject.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
}