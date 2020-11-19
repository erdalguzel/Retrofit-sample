package com.erdalguzel.retrofit_sample.`interface`

import com.erdalguzel.retrofit_sample.model.User
import retrofit2.Call
import retrofit2.http.GET

interface OnGetUsers {

    @GET("/users")
    fun getAllUsers(): Call<List<User>>
}