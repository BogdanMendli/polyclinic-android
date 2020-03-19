package com.example.polyclinic.polyclinic.android.api

import com.example.polyclinic.polyclinic.android.data.User
import com.example.polyclinic.polyclinic.android.data.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("polyclinic/spbstu/users/registration")
    fun registerUser(@Body user: User): Call<Result>

    @GET("polyclinic/spbstu/users/")
    fun getUsers(): Call<List<User>>

    @POST("polyclinic/spbstu/users/login")
    fun login(@Body user: User): Call<Result>
}