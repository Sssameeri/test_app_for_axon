package com.drewrick.testappforaxon.model.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.model.network.NetworkState
import com.drewrick.testappforaxon.model.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PersonsDataSourceFactory(
    private val repository: Repository,
    private val compositeDisposable: CompositeDisposable,
    private val networkState: MutableLiveData<NetworkState>
) : DataSource.Factory<Int, Person>() {

    val sourceLiveData = MutableLiveData<PersonsDataSource>()
    var source: PersonsDataSource? = null

    override fun create(): DataSource<Int, Person> {
        source = PersonsDataSource(repository, compositeDisposable, networkState)
        sourceLiveData.postValue(source)
        return source as PersonsDataSource
    }


}