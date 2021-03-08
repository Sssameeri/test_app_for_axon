package com.drewrick.testappforaxon.model.database.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.drewrick.testappforaxon.model.models.DateOfBirth
import com.google.gson.Gson

class DOBConverter {

    @TypeConverter
    fun dobToString(dob: DateOfBirth): String {
        return Gson().toJson(dob)
    }

    @TypeConverter
    fun stringToObject(dob: String): DateOfBirth {
        return Gson().fromJson(dob, DateOfBirth::class.java)
    }

}