package com.example.mycryptoapp.Pojos.NewsPojos.NewsRequest

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins

data class CoinWithEvents (
    @Embedded
    val coinDetailedInfoByCoins: CoinDetailedInfoByCoins,

    @Relation (
        parentColumn = "fromsymbol",
        entityColumn = "symb"
            )
    val events: List <CoinEvent>
)