package com.example.covidmonitor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidmonitor.R
import com.example.covidmonitor.data.CovidRepository
import kotlinx.android.synthetic.main.fragment_global.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GlobalFagment:Fragment(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_global, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = CovidRepository()
        launch {
            val global = repository.getSummary().await()
            delay(1000)
            loader.visibility = View.GONE
            globalData.visibility = View.VISIBLE

            global?.let {
                newConfirmed.text =getString(R.string.new_confirmed_template).format(it.newConfirmed)
                newDeaths.text = getString(R.string.new_deaths_template).format(it.newDeaths)
                newRecovered.text =getString(R.string.new_recovered_template).format(it.newRecovered)
                totalConfirmed.text =getString(R.string.total_comfirmed_template).format(it.totalConfirmed)
            }
        }

    }
}