package com.erdalguzel.retrofit_sample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    var instance: Retrofit? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com"

    fun getRetrofitInstance(): Retrofit? {
        if (instance == null) {
            instance = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }
        return instance
    }
}