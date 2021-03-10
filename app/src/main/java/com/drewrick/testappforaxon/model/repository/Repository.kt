package com.drewrick.testappforaxon.model.repository

import androidx.paging.DataSource
import com.drewrick.testappforaxon.model.models.Person
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface Repository {
    fun getDataFromNetwork(page: Int, size: Int): Single<List<Person>>
}