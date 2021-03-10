package com.drewrick.testappforaxon.model.repository

import com.drewrick.testappforaxon.BuildConfig
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.model.network.RetrofitInstance
import io.reactivex.Single

class RepositoryImp : Repository {

    private val networkApi = RetrofitInstance.provideNetworkAPI()

    override fun getDataFromNetwork(page: Int, size: Int): Single<List<Person>> {
        return networkApi
            .getData(page, BuildConfig.API_KEY, size)
            .map { response ->
                response.results
            }
    }
}