package com.drewrick.testappforaxon.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.drewrick.testappforaxon.model.database.converters.DOBConverter
import com.drewrick.testappforaxon.model.database.converters.NameConverter
import com.drewrick.testappforaxon.model.database.converters.PictureConverter
import com.drewrick.testappforaxon.model.database.dao.PersonDao
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.utils.Constants

@Database(entities = [Person::class], version = Constants.DATABASE_VERSION, exportSchema = false)
@TypeConverters(DOBConverter::class, NameConverter::class, PictureConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun personDao(): PersonDao
}