package com.drewrick.testappforaxon.model.database.converters

import androidx.room.TypeConverter
import com.drewrick.testappforaxon.model.models.Name
import com.google.gson.Gson

class NameConverter {

    @TypeConverter
    fun nameToString(name: Name): String {
        return Gson().toJson(name)
    }

    @TypeConverter
    fun stringToObject(name: String): Name {
        return Gson().fromJson(name, Name::class.java)
    }

}