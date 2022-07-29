package com.example.weatherinnovosibirsk.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WindInfoDto(
    @SerializedName("speed")
    @Expose
    val speed: Int?,

    @SerializedName("deg")
    @Expose
    val deg: Int?,
)