package com.example.weatherapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiKey = "API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cityInput = findViewById<EditText>(R.id.cityInput)
        val loadWeather = findViewById<Button>(R.id.loadWeather)
        val weatherData = findViewById<TextView>(R.id.weatherData)

        loadWeather.setOnClickListener {
            val city = cityInput.text.toString()

            WeatherService.api.getWeather(city, apiKey)
                .enqueue(object : Callback<WeatherResponse> {

                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        val data = response.body()
                        if (data != null) {
                            weatherData.text = "City: ${data.name}\nTemp: ${data.main.temp}°C"
                        } else {
                            weatherData.text = "City not found!"
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        weatherData.text = t.message
                    }
                })
        }
    }
}
