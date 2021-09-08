package com.example.mycryptoapp.API

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {

    private const val BASIC_INFO_BASE_URL = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMAGE_URL = "https://cryptocompare.com"
    private const val NEWS_BY_COIN_BASE_URL = "https://coinpaprika1.p.rapidapi.com/"

    private val retrofitBasicInfo = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASIC_INFO_BASE_URL)
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    private val retrofitNews = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(NEWS_BY_COIN_BASE_URL)
        .client(okHttpClient)
        .build()


    val newsAPIService = retrofitNews.create(NewsAPIService::class.java)



    val basicInfoAPIServices = retrofitBasicInfo.create(BasicInfoAPIServices::class.java)
}