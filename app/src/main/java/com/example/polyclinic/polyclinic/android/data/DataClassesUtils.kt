package com.example.polyclinic.polyclinic.android.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("password")
    @Expose
    val password: String
)

data class Role(
    val id: Long,
    val name: String
)

data class Ward(
    val id: Int,
    val name: String,
    val maxCount: Int
)

data class Diagnosis(
    val id: Int,
    val name: String
)

data class People(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val diagnosisId: Int,
    val wardId: Int
)

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    @SerializedName("body")
    val text: String
)

data class Result(
    @SerializedName("result")
    @Expose
    val result: Boolean
)