package com.drewrick.testappforaxon.model.database.converters

import androidx.room.TypeConverter
import com.drewrick.testappforaxon.model.models.Picture
import com.google.gson.Gson

class PictureConverter {
    @TypeConverter
    fun convertToString(picture: Picture): String {
        return Gson().toJson(picture)
    }

    @TypeConverter
    fun convertToObject(picture: String): Picture {
        return Gson().fromJson(picture, Picture::class.java)
    }
}