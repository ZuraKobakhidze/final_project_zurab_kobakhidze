package com.example.final_project_zurab_kobakhidze

import android.graphics.drawable.Drawable
import java.io.InputStream
import java.net.URL


class LoadImageUseCase {

    fun LoadImageFromWebOperations(url: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).getContent() as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: Exception) {
            null
        }
    }

}