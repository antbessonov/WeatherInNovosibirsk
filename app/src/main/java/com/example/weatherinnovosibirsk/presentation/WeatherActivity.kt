package com.example.weatherinnovosibirsk.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.weatherinnovosibirsk.R
import com.example.weatherinnovosibirsk.databinding.ActivityWeatherBinding
import com.example.weatherinnovosibirsk.domain.WeatherInfo
import com.squareup.picasso.Picasso

class WeatherActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWeatherBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.weatherInfo.observe(this) {
            setValueView(it)
        }
    }

    private fun setValueView(weatherInfo: WeatherInfo) {
        with(binding) {
            Picasso.get().load(weatherInfo.icon).into(weatherIconImage)
            cityNameTv.text = weatherInfo.name
            tempTv.text = String.format(
                getString(R.string.temp_text),
                weatherInfo.temp
            )
            tempFeelsLikeTv.text = String.format(
                getString(R.string.temp_feels_like_text),
                weatherInfo.feelsLike
            )
            humidityValueTv.text = weatherInfo.humidity
            dtTv.text = String.format(
                getString(R.string.dt_text),
                weatherInfo.dt
            )
            pressureValueTv.text = weatherInfo.pressure
            windSpeedAndDegValueTv.text = String.format(
                getString(R.string.wind_speed_and_deg_value_text),
                weatherInfo.speed,
                weatherInfo.deg
            )
        }
    }

}