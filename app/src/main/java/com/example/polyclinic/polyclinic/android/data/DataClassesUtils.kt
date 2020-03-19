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
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("maxCount")
    @Expose
    val maxCount: Int,
    @SerializedName("peopleEntities")
    @Expose
    val patients: List<People>
)

data class Diagnosis(
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String
)

data class People(
    val id: Int,
    @SerializedName("firstName")
    @Expose
    val firstName: String,
    @SerializedName("lastName")
    @Expose
    val lastName: String,
    @SerializedName("fatherName")
    @Expose
    val fatherName: String,
    @SerializedName("diagnosisByDiagnosisId")
    @Expose
    val diagnosis: Diagnosis,
    @SerializedName("wardsByWardId")
    @Expose
    val ward: Ward
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