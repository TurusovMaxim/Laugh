package com.example.laugh.network.response

import com.example.laugh.network.response.UserInfo
import com.google.gson.annotations.SerializedName

data class UserInfoBase (
        @SerializedName("accessToken")
        val accessToken : String,
        @SerializedName("userInfo")
        val userInfo : UserInfo
)