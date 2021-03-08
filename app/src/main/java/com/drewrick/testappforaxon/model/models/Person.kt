package com.drewrick.testappforaxon.model.models

import androidx.room.*
import com.drewrick.testappforaxon.model.database.converters.DOBConverter
import com.drewrick.testappforaxon.model.database.converters.NameConverter
import com.drewrick.testappforaxon.model.database.converters.PictureConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Person")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("cell")
    val cell: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("dob")
    val dob: DateOfBirth,
    @SerializedName("name")
    @TypeConverters(NameConverter::class)
    val name: Name,
    @SerializedName("picture")
    @TypeConverters(PictureConverter::class)
    val picture: Picture,
)