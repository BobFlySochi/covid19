package com.example.covidmonitor.data

import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("Global") val global: Global
)

data class Global(
    @SerializedName("NewConfirmed") val newConfirmed: Int,
    @SerializedName("NewDeaths") val newDeaths: Int,
    @SerializedName("NewRecovered") val newRecovered: Int,
    @SerializedName("TotalRecovered") val totalRecovered: Int,
    @SerializedName("TotalConfirmed") val totalConfirmed:Int,
    @SerializedName("TotalDeaths") val totalDeaths:Int

)

data class CountryCases(
    @SerializedName("Country") val country: String,
    @SerializedName("Cases") val cases: Int
)

data class LastCases(
    @SerializedName("Country") val country: String,
    @SerializedName("Confirmed") val confirmed: Int,
    @SerializedName("Deaths") val deaths: Int,
    @SerializedName("Recovered") val recovered: Int
)