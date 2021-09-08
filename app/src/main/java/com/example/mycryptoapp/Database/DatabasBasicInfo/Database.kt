package com.example.mycryptoapp.Database.DatabasBasicInfo

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins


@androidx.room.Database(entities = [CoinDetailedInfoByCoins::class], version = 14, exportSchema = false)
abstract class Database : RoomDatabase() {
    companion object {
        private var db: Database? = null
        private const val DB_NAME = "coins.db"


        fun getInstance (context: Context): Database {
            if (db != null) {
                db?.let {
                    return db!!
                }
            }
            val instance = Room.databaseBuilder(context, Database::class.java, DB_NAME).fallbackToDestructiveMigration().build()
            db = instance
            return instance
        }

    }

    abstract fun coinInfoDao (): CoinDao
}