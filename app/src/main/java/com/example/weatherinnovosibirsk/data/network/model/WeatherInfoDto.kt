package com.example.weatherinnovosibirsk.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherInfoDto(
    @SerializedName("weather")
    @Expose
    val weather: List<WeatherConditionInfoDto>?,

    @SerializedName("main")
    @Expose
    val main: WeatherMainInfoDto?,

    @SerializedName("wind")
    @Expose
    val wind: WindInfoDto?,

    @SerializedName("dt")
    @Expose
    val dt: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,
)