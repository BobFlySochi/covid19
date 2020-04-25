package com.example.covidmonitor.data

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.annotation.RetentionPolicy
import kotlin.coroutines.CoroutineContext

class CovidRepository : CoroutineScope {
    override val coroutineContext = Dispatchers.IO
    private val covidApi = Retrofit.Builder()
        .baseUrl(CovidApi.HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CovidApi::class.java)

    fun getSummary() = async {
       covidApi.getSummary()
            .execute()
            .body()

    }
    fun getRussiaLiveData() = async {
        covidApi.getByCountryLive("russia")
            .execute()
            .body()
            ?.last()

    }
    fun getRussiaLastData() = async {
        covidApi.getByCountryLastCases("russia")
            .execute()
            .body()
            ?.last()
    }
    fun getUkraineLastData() = async {
        covidApi.getByCountryLastCases("ukraine")
            .execute()
            .body()
            ?.last()
    }
}