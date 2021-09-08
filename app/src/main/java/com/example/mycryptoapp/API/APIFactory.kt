package com.example.mycryptoapp.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {

    private const val BASIC_INFO_BASE_URL = "https://min-api.cryptocompare.com/data/"
    const val BASE_IMAGE_URL = "https://cryptocompare.com"

    private val retrofitBasicInfo = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASIC_INFO_BASE_URL)
        .build()

    val basicInfoAPIServices = retrofitBasicInfo.create(BasicInfoAPIServices::class.java)
}