package com.example.covidmonitor.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidApi {
    companion object{
        const val HOST = "https://api.covid19api.com"
    }
    @GET("summary")
    fun getSummary(): Call<Summary>
    @GET("country/{countryName}/status/confirmed")
    fun getByCountryLive(@Path("countryName") countryName:String): Call<List<CountryCases>>
    @GET("total/country/{countryName}")
    fun getByCountryLastCases(@Path("countryName") countryName:String): Call<List<LastCases>>

}