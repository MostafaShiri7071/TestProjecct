package com.my.testproject.core

import android.app.Application
import com.my.testproject.di.adapterModule
import com.my.testproject.di.apiModule
import com.my.testproject.di.netModule
import com.my.testproject.di.repositoryModule
import com.my.testproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Mostafa Shiri.
 */
class TestApp:Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestApp)
            androidLogger(Level.ERROR)
            modules(listOf(netModule, apiModule, repositoryModule, viewModelModule,adapterModule))
        }
    }
}