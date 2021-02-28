package com.my.testproject.di

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.my.testproject.api.ApiService
import com.my.testproject.core.Constant
import com.my.testproject.core.SharedPreferencesHelper
import com.my.testproject.core.Utils
import com.my.testproject.core.sharedPreferencesHelper
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * Created by Mostafa Shiri.
 */

val apiModule = module {
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { provideApiService(get()) }
}

val netModule = module {

    fun provideAuthorization(application: Application): String {
        return application.sharedPreferencesHelper().getString(
            Constant.TOKEN)
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun token(application: Application):SharedPreferencesHelper{
       return SharedPreferencesHelper(application,"User")
    }

    fun tokenReal():String{
        //val b=token().getString("")
        return token(Application()).getString(Constant.TOKEN)
    }

    fun provideHttpClient(cache:Cache,application: Application): OkHttpClient {
       // val to=Application().sharedPreferencesHelper("User").getString(Constant.TOKEN)
        val b=SharedPreferencesHelper(application,"User")
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
        clientBuilder.addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader(
                    "X-Authorization",b.getString(Constant.TOKEN))
                .build()
            chain.proceed(newRequest)
        }

            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .cache(cache)
        return clientBuilder.build()

    }


    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    }

    /*fun Application.sharedPreferencesHelper(name: SharedPreferencesHelper.Name = SharedPreferencesHelper.Name.User):
            SharedPreferencesHelper {
        return SharedPreferencesHelper(this, name)
    }*/

    single { provideCache(androidApplication()) }
    //factory{token(androidApplication())}
    single { provideHttpClient(get(),androidApplication()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
    //single { SharedPreferencesHelper(androidApplication(),"")}
    //single {provideAuthorization(androidApplication())}


    /* fun createNetworkClient(baseUrl: String) =
         retrofitClient(baseUrl, httpClient())


      fun httpClient(): OkHttpClient {

         val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
         val clientBuilder = OkHttpClient.Builder()
         //if (BuildConfig.DEBUG) {
             httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
             clientBuilder.addInterceptor(httpLoggingInterceptor)
        // }
         clientBuilder.addInterceptor { chain ->
             val newRequest = chain.request().newBuilder()
                 .addHeader(
                     "Authorization", "Bearer ${Utils.myyy()}"
                 )
                 .build()
             chain.proceed(newRequest)
         }



         clientBuilder.readTimeout(120, TimeUnit.SECONDS)
         clientBuilder.writeTimeout(120, TimeUnit.SECONDS)
         return clientBuilder.build()
     }

      fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
         Retrofit.Builder()
             .baseUrl(baseUrl)
             .client(httpClient)
             .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build()

     single { createNetworkClient("BuildConfig.BASE_URL") }
     single { (get() as Retrofit).create(Api::class.java) }*/
}