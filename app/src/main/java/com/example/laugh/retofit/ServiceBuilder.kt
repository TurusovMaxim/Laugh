package com.example.laugh.retofit

import com.example.laugh.network.NetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://r2.mocker.surfstudio.ru/android_vsu/"

object ServiceBuilder {
    private var gson: Gson = GsonBuilder().create()

    private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    fun getApi(): NetworkService = retrofit.create(NetworkService::class.java)
}