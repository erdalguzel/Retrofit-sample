package com.erdalguzel.retrofit_sample.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("street") val streetName: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipcode: String
)