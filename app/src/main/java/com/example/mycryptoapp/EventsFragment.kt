package com.example.mycryptoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mycryptoapp.Adapters.RVEventsAdapter


class EventsFragment() : DialogFragment(R.layout.fragment_events) {

    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: RVEventsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventsRecyclerView = view.findViewById(R.id.rv_Events_fr_DA)
        viewModel = ViewModelProvider(requireActivity())[CoinViewModel::class.java]
        adapter = RVEventsAdapter()
        eventsRecyclerView.layoutManager = LinearLayoutManager(view.context)
        eventsRecyclerView.adapter = adapter
        viewModel.getCoinWithEvents().observe(viewLifecycleOwner) {
            it?.let {
                adapter.listOfEvents = it.events.filter {
                    it.date?.substringBefore("-")?.toInt()!! > 2020
                }.sortedByDescending { it.date }
            }
        }
        adapter.onItemClickListener = object : RVEventsAdapter.OnItemClickListener {
            override fun onItemClick(intent: Intent) {
                startActivity(intent)
            }
        }

    }
}