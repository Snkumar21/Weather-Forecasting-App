package com.example.weatherapplication.models

data class WeatherResponse(
    val name: String,
    val main: Main
)

data class Main(
    val temp: Float
)
