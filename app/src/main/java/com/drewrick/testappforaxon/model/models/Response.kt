package com.drewrick.testappforaxon.model.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<Person>
)