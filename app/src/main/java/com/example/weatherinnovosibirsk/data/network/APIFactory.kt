package com.example.weatherinnovosibirsk.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {

    private const val BASE_URL = "https://api.openweathermap.org/data/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService: APIService = retrofit.create(APIService::class.java)
}