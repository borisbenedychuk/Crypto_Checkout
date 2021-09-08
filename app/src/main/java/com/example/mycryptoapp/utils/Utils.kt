package com.example.mycryptoapp.utils

import android.annotation.SuppressLint
import com.example.mycryptoapp.Pojos.NewsPojos.CoinCred.CoinCred
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
object Utils {

    var credList = mutableListOf<CoinCred>()

    fun getCurrentTime(timeStamp: Long): String {
        val stamp = Timestamp(timeStamp)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat (date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val dateF = inputFormat.parse(date)
        val outputFormat = SimpleDateFormat("dd.MM.yyyy")
        return outputFormat.format(dateF!!)
    }


}

