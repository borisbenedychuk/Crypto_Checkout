package com.example.mycryptoapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.Adapters.CoinAdapter
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageViewLoadingMain: ImageView
    private lateinit var progressBarLoadingMain: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageViewLoadingMain = findViewById(R.id.imageViewLoadingBG)
        progressBarLoadingMain = findViewById(R.id.progressBarMain)
        recyclerView = findViewById(R.id.recyclerViewCoinsList)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
        coinAdapter = CoinAdapter(this)
        recyclerView.adapter = coinAdapter
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.loadDataFirst()
        viewModel.loadData()
        viewModel.getCoinCreds()
        viewModel.priceList.observe(this) {
            coinAdapter.list = it
        }
        coinAdapter.onCoinCliCkListener = object : CoinAdapter.OnCoinCliCkListener {
            override fun onCoinClick(coin: CoinDetailedInfoByCoins) {
                intent = DetailedActivity.newIntent(this@MainActivity, coin.fromsymbol)
                startActivity(intent)
            }
        }
    }
}

