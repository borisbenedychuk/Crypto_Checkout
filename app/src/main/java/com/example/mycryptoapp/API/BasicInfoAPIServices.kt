package com.example.mycryptoapp.API

import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfo
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinInfoList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BasicInfoAPIServices {



    @GET ("top/totaltoptiervolfull")
    fun getCoinListData (@Query (API_KEY_PARAM_BASIC) apiKey: String = API_KEY_BASIC_INFO,
                         @Query (LIMIT_PARAM) limit:String = LIMIT_DEFAULT,
                         @Query (TO_SYMBOL_PARAM) tSym: String = TO_SYMBOL_DEFAULT): Observable<CoinInfoList>

    @GET ("pricemultifull")
    fun getCoinListDetailedInfo (@Query (API_KEY_PARAM_BASIC) apiKey: String = API_KEY_BASIC_INFO,
                                 @Query (FROM_SYMBOLS_PARAM) fSyms: String,
                                 @Query (TO_SYMBOLS_PARAM) tSyms: String = TO_SYMBOL_DEFAULT) : Observable<CoinDetailedInfo>

    companion object {
        private const val LIMIT_PARAM = "limit"
        private const val API_KEY_PARAM_BASIC = "api_key"
        private const val TO_SYMBOL_PARAM = "tsym"
        private const val FROM_SYMBOLS_PARAM = "fsyms"
        private const val TO_SYMBOLS_PARAM = "tsyms"
        private const val TO_SYMBOL_DEFAULT = "USD"
        private const val LIMIT_DEFAULT = "10"
        private const val API_KEY_BASIC_INFO = "9f2cca226ff85a86b52293fdf7599912a4ace291d7b6d17412a7dde2836bdf5b"

    }
}