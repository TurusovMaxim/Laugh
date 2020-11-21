package com.example.laugh.data.network.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse (
        @SerializedName("accessToken")
        val accessToken : String? = null,
        @SerializedName("userInfo")
        val userInfo : UserInfo? = null
)