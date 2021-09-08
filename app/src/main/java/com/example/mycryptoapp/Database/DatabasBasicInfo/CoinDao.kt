package com.example.mycryptoapp.Database.DatabasBasicInfo

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins
import com.example.mycryptoapp.Pojos.NewsPojos.NewsRequest.CoinEvent
import com.example.mycryptoapp.Pojos.NewsPojos.NewsRequest.CoinWithEvents


@Dao
interface CoinDao {
    @Query("SELECT * FROM coins ORDER BY volume24hourto DESC")
    fun getListOfDetailedCoins(): LiveData<List<CoinDetailedInfoByCoins>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<CoinDetailedInfoByCoins>)

    @Query("DELETE FROM coins")
    fun deleteCoins ()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinEvents  (list: List <CoinEvent>)

    @Query("DELETE FROM events WHERE symb = :symb")
    fun deleteCoinEvents (symb: String)

    @Transaction
    @Query("SELECT * FROM coins WHERE fromsymbol = :fsym")
    fun getCoinWithNews (fsym: String): LiveData<CoinWithEvents>

}