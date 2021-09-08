package com.example.mycryptoapp.Pojos.NewsPojos.CoinCred

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class CoinCred (
    @SerializedName("id")
    @Expose
    private val id: String? = null,

    @SerializedName("symbol")
    @Expose
    private val symbol: String? = null,

    @SerializedName("is_new")
    @Expose
    private val isNew: Boolean = false,

)