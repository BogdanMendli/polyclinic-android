package com.example.polyclinic.polyclinic.android.api

import com.example.polyclinic.polyclinic.android.data.Result
import com.example.polyclinic.polyclinic.android.data.People
import com.example.polyclinic.polyclinic.android.data.Ward
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PolyclinicApiService {

    @GET("polyclinic/spbstu/users/people/")
    fun getUsers(): Call<List<People>>

    @GET("polyclinic/spbstu/users/ward/")
    fun getWards(): Call<List<Ward>>

    @POST("/polyclinic/spbstu/users/people/add")
    fun addPatient(@Body patient: People): Call<Result>
}