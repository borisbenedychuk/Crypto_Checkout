package com.example.mycryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycryptoapp.API.APIFactory
import com.example.mycryptoapp.API.BasicInfoAPIServices
import com.example.mycryptoapp.Database.DatabasBasicInfo.Database
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins
import com.example.mycryptoapp.Pojos.NewsPojos.CoinCred.CoinCred
import com.example.mycryptoapp.utils.Utils.credList
import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private var compositeDisposable = CompositeDisposable()
    private lateinit var apiServices: BasicInfoAPIServices
    private val db = Database.getInstance(application)
    var priceList = db.coinInfoDao().getListOfDetailedCoins()
    private var coinLiveData = MutableLiveData<String>()

    fun setCoin (fSym: String) {
        coinLiveData.value = fSym
    }

    fun getCoinLiveData (): LiveData<String> = coinLiveData

    fun getCoinWithEvents () = db.coinInfoDao().getCoinWithNews(coinLiveData.value!!)

    fun getCoinCreds () {
        val disposable = APIFactory.newsAPIService
            .getAvailableCoins()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list, throwable ->
                throwable?.let {
                    Log.d("Test_coin_creds", "problems with request")
                }
                list?.let {
                    credList.addAll(it)
                }
            }
        compositeDisposable.add(disposable)

    }


    fun loadCoinEvents () {
        Log.d("Test_News_Api", credList.size.toString())
        if (credList.isNotEmpty()) {
            for (coinCred in credList) {
                if (coinCred.symbol == coinLiveData.value) {
                    loadEvents(coinCred)
                }
            }
        }

    }

    private fun loadEvents (coinCred: CoinCred) {
        val disposable = APIFactory.newsAPIService
            .getNewsByCoin(coinCred.id!!)
            .subscribeOn(Schedulers.io())
            .subscribe { list, throwable ->
                throwable?.let {
                    Log.d("Test_News_Api" , throwable.message!!)
                }
                list?.let {
                    it.map {
                        it.symb = coinCred.symbol
                    }
                    Log.d("Test_News_Api", "Im in last call!")
                    db.coinInfoDao().deleteCoinEvents(coinCred.symbol!!)
                    db.coinInfoDao().insertCoinEvents(it)
                }
            }
        compositeDisposable.add(disposable)
    }

    fun loadDataFirst() {
        apiServices = APIFactory.basicInfoAPIServices
        val disposable = apiServices.getCoinListData()
            .map { it.data?.map { it?.coinInfo?.name }!!.joinToString(",") }
            .flatMap { APIFactory.basicInfoAPIServices.getCoinListDetailedInfo(fSyms = it) }
            .map { it.coinDetailedInfo }
            .map { getListOfDetailedCoinsFromJson(it)  }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinInfoDao().deleteCoins()
                db.coinInfoDao().insertList(it)
            }, {
                Log.d("TEST1", it?.message ?: "")
            })
        compositeDisposable.add(disposable)
    }

    fun loadData() {
        apiServices = APIFactory.basicInfoAPIServices
        val disposable = apiServices.getCoinListData()
            .map { it.data?.map { it?.coinInfo?.name }!!.joinToString(",") }
            .flatMap { APIFactory.basicInfoAPIServices.getCoinListDetailedInfo(fSyms = it) }
            .map { it.coinDetailedInfo }
            .map { with(it) { getListOfDetailedCoinsFromJson(it) } }
            .delay(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinInfoDao().insertList(it)
            }, {
                Log.d("TEST1", it?.message ?: "")
            })
        compositeDisposable.add(disposable)
    }




    fun getListOfDetailedCoinsFromJson(jsonObject: JsonObject?): List<CoinDetailedInfoByCoins> {
        val result = mutableListOf<CoinDetailedInfoByCoins>()
        if (jsonObject == null) {
            return result
        }
        val coinsKeySet = jsonObject.keySet()
        for (i in coinsKeySet) {
            val jsonCurrency = jsonObject[i].asJsonObject
            val currencyKeyset = jsonCurrency.keySet()
            for (k in currencyKeyset) {
                val coinInfo = Gson().fromJson(
                    jsonCurrency[k],
                    CoinDetailedInfoByCoins::class.java
                )
                result.add(coinInfo)
            }

        }
        return result
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}