package com.example.final_project_zurab_kobakhidze

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.net.URL

class HomeActivity : AppCompatActivity() {

    //region VIEW
    lateinit var recyclerView: RecyclerView
    lateinit var cityText: EditText
    lateinit var addCityButton: Button
    //endregion

    //region DEPENDENCY
    private lateinit var client: OkHttpClient
    private lateinit var adapter: MyAdapter
    //endregion

    //region DATA
    private var weatherList = arrayListOf<WeatherModel>()
    //endregion

    //region CREATION
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        recyclerView = findViewById(R.id.recyclerview)
        cityText = findViewById(R.id.cityText)
        addCityButton = findViewById(R.id.addCityButton)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        client = OkHttpClient()
        adapter = MyAdapter(weatherList)

        recyclerView.adapter = adapter

        addCityButton.setOnClickListener {
            onAddCity()
        }

    }
    //endregion

    //region GET
    private fun getData(url: String): Unit {
        val request = Request.Builder()
            .url(url)
            .build()

        return client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                failureAccured()
            }
            override fun onResponse(call: Call, response: Response) = parseJSON(response.body()?.string()
                .toString())
        })
    }

    fun getImageFromURL(url: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).getContent() as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: Exception) {
            null
        }
    }
    //endregion

    //region HELPER
    private fun failureAccured() {
        Toast.makeText(this, "Can't connect to server", Toast.LENGTH_SHORT).show()
    }

    private fun parseJSON(json: String) {
        val weather = Gson().fromJson(json, MWeather::class.java)
        print(weather)
        addWeather(weather)
    }
    //endregion

    //region ADD
    private fun addWeather(weather: MWeather) {

        val city = weather.name ?: ""
        val title = weather.weather?.first()?.main ?: ""
        val temp = weather.main?.temp?.toInt().toString() + "°" ?: ""
        val coverImage = getImageFromURL("http://openweathermap.org/img/wn/" + (weather.weather?.first()?.icon ?: "")  + "@2x.png")

        print(coverImage)

        val weatherModel = WeatherModel(
            city,
            title,
            temp,
            coverImage
        )

        weatherList.add(weatherModel)

        addCityButton.isClickable = true

    }
    //endregion

    //region ACTION
    private fun onAddCity() {

        addCityButton.isClickable = false

        val city = cityText.text.toString()
        cityText.setText("")
        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=83657c006d7b676930cfa2715624dba8&units=metric"

        getData(url)

    }
    //endregion

}