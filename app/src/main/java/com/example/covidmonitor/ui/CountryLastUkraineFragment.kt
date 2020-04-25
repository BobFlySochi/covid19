package com.example.covidmonitor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidmonitor.R
import com.example.covidmonitor.data.CovidRepository
import kotlinx.android.synthetic.main.fragment_country_data.loader
import kotlinx.android.synthetic.main.fragment_last_russia_data.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountryLastUkraineFragment : Fragment(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_last_russia_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repoLastDate = CovidRepository()

        launch {
            val countryLastCases = repoLastDate.getUkraineLastData().await()
            loader.visibility = View.GONE
            countryByCountryLast.visibility = View.VISIBLE
            deathsByCountryLast.visibility = View.VISIBLE
            confirmedByCountryLast.visibility = View.VISIBLE
            recoveredByCountryLast.visibility = View.VISIBLE

            countryLastCases?.let {
                countryByCountryLast.text = it.country
                deathsByCountryLast.text = getString(R.string.all_deaths_template).format(it.deaths)
                confirmedByCountryLast.text =
                    getString(R.string.all_confirmed_template).format(it.confirmed)
                recoveredByCountryLast.text =
                    getString(R.string.all_recovered_template).format(it.recovered)


            }
        }
    }
}