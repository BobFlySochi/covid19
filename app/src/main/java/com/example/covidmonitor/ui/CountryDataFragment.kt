package com.example.covidmonitor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidmonitor.R
import com.example.covidmonitor.data.CovidRepository
import kotlinx.android.synthetic.main.fragment_country_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CountryDataFragment:Fragment(), CoroutineScope {
    override val coroutineContext=Dispatchers.Main
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repo = CovidRepository()

        launch {
            val countryCases = repo.getRussiaLiveData().await()

            delay(1000)
            loader.visibility =View.GONE
            countryData.visibility= View.VISIBLE

            countryCases?.let {
                country.text = it.country
                cases.text = getString(R.string.new_confirmed_template).format(it.cases)
            }
        }
    }
}