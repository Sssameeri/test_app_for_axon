package com.drewrick.testappforaxon.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.drewrick.testappforaxon.model.models.Person
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface PersonDao {

    @Insert
    fun insert(personList: List<Person>): Completable

    @Query("SELECT * FROM person")
    fun getPersons(): Observable<List<Person>>
}