package com.example.mycryptoapp.Pojos.BasicInfoPojos

import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinInfo
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)