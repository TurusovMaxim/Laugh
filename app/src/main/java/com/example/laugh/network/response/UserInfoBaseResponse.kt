package com.example.laugh.network.response

import com.google.gson.annotations.SerializedName

data class UserInfoBaseResponse (
        @SerializedName("accessToken")
        val accessToken : String? = null,
        @SerializedName("userInfo")
        val userInfo : UserInfoResponse? = null
)