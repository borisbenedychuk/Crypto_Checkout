package com.example.mycryptoapp.utils

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


}

