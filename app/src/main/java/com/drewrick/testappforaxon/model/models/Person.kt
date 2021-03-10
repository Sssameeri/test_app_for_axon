package com.drewrick.testappforaxon.model.models

data class Person(
    val cell: String,
    val phone: String,
    val email: String,
    val gender: String,
    val dob: DateOfBirth,
    val name: Name,
    val picture: Picture,
)