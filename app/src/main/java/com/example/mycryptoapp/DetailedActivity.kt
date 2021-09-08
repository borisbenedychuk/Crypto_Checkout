package com.example.mycryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class DetailedActivity : AppCompatActivity() {


    private lateinit var viewmodel: CoinViewModel
    private lateinit var textViewFullName: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textView24HourChange: TextView
    private lateinit var textView24HourVol: TextView
    private lateinit var textViewMin: TextView
    private lateinit var textViewMax: TextView
    private lateinit var imageView: ImageView
    private lateinit var newTV: TextView
    private lateinit var buttonEvents: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        textViewFullName = findViewById(R.id.textViewFullName)
        textViewPrice = findViewById(R.id.textViewPriceDet)
        textView24HourChange = findViewById(R.id.textViewChange24HourDet)
        textView24HourVol = findViewById(R.id.textView24HourVolDet)
        textViewMin = findViewById(R.id.textViewMinDet)
        textViewMax = findViewById(R.id.textViewMaxDet)
        imageView = findViewById(R.id.imageView2)
        buttonEvents = findViewById(R.id.eventsButton_DA)
        newTV = findViewById(R.id.tvNew_DA)
        viewmodel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (intent.hasExtra(FROM_SYMBOL)) {
            val fSymb = intent.getStringExtra(FROM_SYMBOL)
            fSymb?.let {
                viewmodel.setCoin(it)
            }
            viewmodel.getCoinLiveData().observe(this) {
                it.let {
                    viewmodel.loadCoinEvents()
                    viewmodel.getCoinWithEvents().observe(this) {
                        it.coinDetailedInfoByCoins.also {
                            textViewFullName.text = it.fromsymbol
                            textView24HourChange.text = String.format("%s US$", DecimalFormat("#0.00").format(it.change24hour).toString() )
                            textView24HourVol.text = String.format("%s US$",
                                DecimalFormat("#0.00").format(it.volume24hourto / 100_000_000))
                            textViewPrice.text = String.format("%s US$",
                                DecimalFormat("#0.00").format(it.price))
                            textViewMax.text = String.format("%s US$",
                                DecimalFormat("#0.00").format(it.high24hour))
                            textViewMin.text = String.format("%s US$",
                                DecimalFormat("#0.00").format(it.low24hour))
                            Picasso.get().load(it.getFullImageUrl()).into(imageView)
                        }
                        val list = it.events.filter {
                            it.date?.substringBefore("-")?.toInt()!! > 2020
                        }
                        if (list.isNotEmpty()) {
                            buttonEvents.setOnClickListener {
                                val dialogFragment = EventsFragment()

                                dialogFragment.show(supportFragmentManager, "events_dialog")
                            }
                        } else {
                            buttonEvents.setOnClickListener {
                                buttonEvents.text = resources.getString(R.string.no_news)
                            }
                        }
                    }
                }
            }


        }
    }

    companion object {
        private const val FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fSym: String): Intent {
            val intent = Intent(context, DetailedActivity::class.java)
            intent.putExtra(FROM_SYMBOL, fSym)
            return intent
        }
    }


}