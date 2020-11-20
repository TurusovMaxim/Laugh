package com.example.laugh.data

import com.example.laugh.data.network.request.AuthRequest
import com.example.laugh.data.network.response.UserInfoBaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

const val API_ROUTE: String = "/auth/login"

interface NetworkService {

    @POST(API_ROUTE)
    fun logIn(@Body userData: AuthRequest): Call<UserInfoBaseResponse>
}