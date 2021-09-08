package com.example.mycryptoapp.Pojos.BasicInfoPojos

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



data class CoinDetailedInfo (
    @SerializedName("RAW")
    @Expose
    var coinDetailedInfo: JsonObject? = null
)