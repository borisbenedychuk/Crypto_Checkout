package com.example.mycryptoapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.Pojos.BasicInfoPojos.CoinDetailedInfoByCoins
import com.example.mycryptoapp.R
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class CoinAdapter(context: Context) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {



    var onCoinCliCkListener: OnCoinCliCkListener? = null
    var list: List<CoinDetailedInfoByCoins> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface OnCoinCliCkListener {
        fun onCoinClick (coin: CoinDetailedInfoByCoins)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coin_item, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coinDetailedInfoByCoins = list[position]
        holder.textViewPair.text = "${coinDetailedInfoByCoins.fromsymbol}/${coinDetailedInfoByCoins.tosymbol}"
        holder.textViewPrice.text = (DecimalFormat("#0.00").format(coinDetailedInfoByCoins.price)).toString() + "$"
        holder.textView24hVol.text = "24Hour vol: " + (DecimalFormat("#0.00").format(coinDetailedInfoByCoins.volume24hourto/100000000).toString()) + "B US$"
        Picasso.get().load(coinDetailedInfoByCoins.getFullImageUrl()).into(holder.imageViewIcon)
        holder.itemView.setOnClickListener {
            onCoinCliCkListener?.onCoinClick(coinDetailedInfoByCoins)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPair: TextView = itemView.findViewById(R.id.textViewPair)
        val textView24hVol: TextView = itemView.findViewById(R.id.textView24hVol)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val imageViewIcon: ImageView = itemView.findViewById(R.id.imageView)
    }
}