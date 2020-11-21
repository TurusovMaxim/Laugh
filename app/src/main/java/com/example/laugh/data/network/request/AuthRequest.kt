package com.example.laugh.data.network.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
        @SerializedName("login")
        val login: String? = null,
        @SerializedName("password")
        val password: String? = null
)