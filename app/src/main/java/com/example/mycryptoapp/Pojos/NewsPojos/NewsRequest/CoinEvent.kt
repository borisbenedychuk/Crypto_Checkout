package com.example.mycryptoapp.Pojos.NewsPojos.NewsRequest

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



@Entity(tableName = "events")
data class CoinEvent (
    @PrimaryKey(autoGenerate = true)
    val idAG: Int,

    var symb: String? = null,

    @SerializedName("date")
    @Expose
    val date: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("link")
    @Expose
    val link: String? = null,

    @SerializedName("proof_image_link")
    @Expose
    val proofImageLink: String? = null
)