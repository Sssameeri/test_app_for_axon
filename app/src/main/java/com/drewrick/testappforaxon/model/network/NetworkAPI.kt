package com.drewrick.testappforaxon.model.network

import com.drewrick.testappforaxon.model.models.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {
    @GET("api/")
    fun getData(
        @Query("key") apiKey: String,
        @Query("results") resultNum: Int
    ): Single<Response>
}