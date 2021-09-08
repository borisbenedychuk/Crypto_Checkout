package com.example.mycryptoapp.Adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.Pojos.NewsPojos.NewsRequest.CoinEvent
import com.example.mycryptoapp.R
import com.example.mycryptoapp.utils.Utils
import com.squareup.picasso.Picasso

class RVEventsAdapter: RecyclerView.Adapter<RVEventsAdapter.RVEventsViewHolder>(){

    var listOfEvents: List <CoinEvent> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick (intent: Intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = RVEventsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.events_item_fr_da, parent, false))


    override fun onBindViewHolder(holder: RVEventsViewHolder, position: Int) {
        val coinEvent = listOfEvents[position]
        holder.eventTitle.text = coinEvent.name
        holder.eventDate.text = Utils.convertDateFormat(coinEvent.date!!)
        holder.cardView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(coinEvent.link))
            onItemClickListener?.onItemClick(intent)
        }

    }

    override fun getItemCount(): Int = listOfEvents.size

    inner class RVEventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventTitle = view.findViewById<TextView>(R.id.tv_EventTitle_fr_DA)
        val eventDate = view.findViewById<TextView>(R.id.tv_Date_fr_DA)
        val cardView = view.findViewById<CardView>(R.id.cv_Event_fr_DA)
    }
}