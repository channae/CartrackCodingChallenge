package com.channa.cartrackcodingchallenge.data.source.remote

import com.channa.cartrackcodingchallenge.data.source.remote.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    // get users
    @GET("/users")
    fun getUsers(): Single<List<UserResponse>>

}