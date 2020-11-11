package com.example.laugh.network.response

import com.google.gson.annotations.SerializedName

data class UserInfo (
        @SerializedName("id")
        val id : Int? = null,
        @SerializedName("username")
        val username : String? = null,
        @SerializedName("firstName")
        val firstName : String? = null,
        @SerializedName("lastName")
        val lastName : String? = null,
        @SerializedName("userDescription")
        val userDescription : String? = null
)