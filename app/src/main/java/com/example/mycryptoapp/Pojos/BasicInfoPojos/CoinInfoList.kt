package com.example.mycryptoapp.Pojos.BasicInfoPojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoList (

    @SerializedName("Data")
    @Expose
    var data: List<Datum?>? = null

)