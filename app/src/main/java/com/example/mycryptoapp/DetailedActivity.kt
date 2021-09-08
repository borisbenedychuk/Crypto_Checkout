package com.example.mycryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var newTextView: TextView



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
        newTextView = findViewById(R.id.tvNew_DA)
        viewmodel = ViewModelProvider(this)[CoinViewModel::class.java]
        var fSymb: String? = null
        if (intent.hasExtra(FROM_SYMBOL)) {
            fSymb = intent.getStringExtra(FROM_SYMBOL)
        }
        if (fSymb == null) {
            finish()
            return
        }
        viewmodel.isHot(fSymb)
        viewmodel.loadCoinEvents(fSymb)
        viewmodel.isHot.observe(this) {
            if (it) {
                newTextView.visibility = View.VISIBLE
            } else {
                newTextView.visibility = View.GONE
            }
        }
        viewmodel.getCoin(fSymb).observe(this) {
            textViewFullName.text = it.fromsymbol
            textView24HourChange.text = DecimalFormat("#0.00").format(it.change24hour).toString() + " US$"
            textView24HourVol.text = DecimalFormat("#0.00").format(it.volume24hourto/100_000_000).toString() + "B US$"
            textViewPrice.text = DecimalFormat("#0.00").format(it.price).toString()+ " US$"
            textViewMax.text = DecimalFormat("#0.00").format(it.high24hour).toString()+ " US$"
            textViewMin.text = DecimalFormat("#0.00").format(it.low24hour).toString()+ " US$"
            Picasso.get().load(it.getFullImageUrl()).into(imageView)
        }
        viewmodel.getEventsByCoin(fSymb).observe(this) {
            val list = it.flatMap { it.events }
            if (list.isNotEmpty()) {
                Log.d("Test_Room_Relational" , list.toString())
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