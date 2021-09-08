package com.example.mycryptoapp.API

import com.example.mycryptoapp.Pojos.NewsPojos.CoinCred.CoinCred
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsAPIService {

    @Headers ("x-rapidapi-host: coinpaprika1.p.rapidapi.com",
    "x-rapidapi-key: 56ce2e36d8msh8e21e2fce855962p1cfe86jsn5abaa566032c")
    @GET("coins")
    fun getAvailableCoins() : Single<List<CoinCred>>
}