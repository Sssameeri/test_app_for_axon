package com.drewrick.testappforaxon.model.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.model.network.NetworkState
import com.drewrick.testappforaxon.model.repository.Repository
import com.google.android.play.core.internal.n
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.Result.response


class PersonsDataSource(
    private val repository: Repository,
    private val compositeDisposable: CompositeDisposable,
    private val networkState: MutableLiveData<NetworkState>
) : PageKeyedDataSource<Int, Person>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Person>
    ) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(repository
            .getDataFromNetwork(1, params.requestedLoadSize)
            .doOnSuccess {
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it, null, 21)
            }
            .subscribe({}, {
                networkState.postValue(NetworkState.error(it.message))
            })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Person>) {

        networkState.postValue(NetworkState.LOADING)

        val nextKey: Int = params.key + 1

        compositeDisposable.add(
            repository.getDataFromNetwork(params.key, params.requestedLoadSize)
                .doOnSuccess {
                    networkState.postValue(NetworkState.LOADED)
                    callback.onResult(it, nextKey)
                }
                .subscribe({}, {
                    networkState.postValue(NetworkState.error(it.message))
                })
        )
    }

}