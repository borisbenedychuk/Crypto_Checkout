package com.example.mycryptoapp.Database.DatabasBasicInfo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins


@Dao
interface CoinDao {
    @Query("SELECT * FROM coins ORDER BY volume24hourto DESC")
    fun getListOfDetailedCoins(): LiveData<List<CoinDetailedInfoByCoins>>

    @Query("SELECT * FROM coins WHERE fromsymbol == :fsym")
    fun getDetailedCoin(fsym: String): LiveData<CoinDetailedInfoByCoins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<CoinDetailedInfoByCoins>)

    @Query("DELETE FROM coins")
    fun deleteCoins ()
}