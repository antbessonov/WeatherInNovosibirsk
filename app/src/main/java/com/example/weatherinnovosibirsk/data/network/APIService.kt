package com.example.weatherinnovosibirsk.data.network

import com.example.weatherinnovosibirsk.data.network.model.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    companion object {

        private const val QUERY_PARAM_CITY_NAME = "q"
        private const val QUERY_PARAM_API_KEY = "appid"
        private const val QUERY_PARAM_LANGUAGE = "lang"
        private const val QUERY_PARAM_UNITS_OF_MEASUREMENT = "units"

        private const val CITY_NAME = "Novosibirsk"
        private const val API_KEY = "031d70ef1a995ee08844b0410a1d62cf"
        private const val LANGUAGE = "ru"
        private const val UNITS_OF_MEASUREMENT = "metric"
    }

    @GET("2.5/weather")
    suspend fun getWeatherInfo(
        @Query(QUERY_PARAM_CITY_NAME) cityName: String = CITY_NAME,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PARAM_UNITS_OF_MEASUREMENT) unitsOfMeasurement: String = UNITS_OF_MEASUREMENT,
    ): WeatherInfoDto
}