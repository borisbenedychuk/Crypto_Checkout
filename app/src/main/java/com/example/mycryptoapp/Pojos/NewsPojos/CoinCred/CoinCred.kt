package com.example.mycryptoapp.Pojos.NewsPojos.CoinCred

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CoinCred (
    @SerializedName("id")
    @Expose
    val id: String? = null,

    @SerializedName("symbol")
    @Expose
    val symbol: String? = null,

    @SerializedName("is_new")
    @Expose
    val isNew: Boolean = false,

)