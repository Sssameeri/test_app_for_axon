package com.drewrick.testappforaxon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.model.network.NetworkState
import com.drewrick.testappforaxon.model.pagination.PersonsDataSourceFactory
import com.drewrick.testappforaxon.model.repository.RepositoryImp
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors

class PersonsViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val executor = Executors.newFixedThreadPool(5)
    private val repository = RepositoryImp()
    val networkState = MutableLiveData<NetworkState>()
    private val dataSourceFactory =
        PersonsDataSourceFactory(repository, compositeDisposable, networkState)

    var personsList: LiveData<PagedList<Person>> =
        dataSourceFactory.toLiveData(
            initialLoadKey = 10,
            pageSize = 20,
            fetchExecutor = executor
        )


    fun invalidateDataSource() {
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }

    private val mutablePerson = MutableLiveData<Person>()
    val selectedPerson: LiveData<Person> get() = mutablePerson

    fun selectedPerson(person: Person) {
        mutablePerson.value = person
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}