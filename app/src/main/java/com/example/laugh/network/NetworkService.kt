package com.example.laugh.network

import com.example.laugh.network.request.AuthRequest
import com.example.laugh.network.response.UserInfoBaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

const val API_ROUTE: String = "/auth/login"

interface NetworkService {

    @POST(API_ROUTE)
    fun logIn(@Body userData: AuthRequest): Call<UserInfoBaseResponse>
}