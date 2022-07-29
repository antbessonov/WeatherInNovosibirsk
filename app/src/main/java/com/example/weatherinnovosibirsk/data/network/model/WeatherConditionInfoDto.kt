package com.example.weatherinnovosibirsk.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherConditionInfoDto(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("main")
    @Expose
    val main: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("icon")
    @Expose
    val icon: String?,
)
