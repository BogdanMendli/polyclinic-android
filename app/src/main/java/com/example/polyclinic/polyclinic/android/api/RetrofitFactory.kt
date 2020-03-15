package com.example.polyclinic.polyclinic.android.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    @JvmStatic
    fun <T> create(clazz: Class<T>, url: String): T {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
            .create(clazz)
    }
}