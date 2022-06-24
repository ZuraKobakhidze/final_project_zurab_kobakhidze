package com.example.final_project_zurab_kobakhidze

import android.graphics.drawable.Drawable

data class WeatherModel(
    var city: String,
    var title: String,
    var temp: String,
    var coverImage: Drawable?) {
}