package com.example.laugh.data.storage

import android.content.Context
import com.example.laugh.data.network.response.UserInfoResponse
import com.example.laugh.util.*
import com.example.laugh.data.storage.SharedPrefStart.get
import com.example.laugh.data.storage.SharedPrefStart.set

class UserStorage(context: Context) {

    private val sharedPref = SharedPrefStart.sharedPrefStart(context)

    fun setUserData(user: UserInfoResponse) {
        sharedPref[ACCESS_TOKEN] = user.accessToken
        sharedPref[USER_ID] = user.userInfo?.id
        sharedPref[USERNAME] = user.userInfo?.username
        sharedPref[FIRST_NAME] = user.userInfo?.firstName
        sharedPref[LAST_NAME] = user.userInfo?.lastName
        sharedPref[USER_DESCRIPTION] = user.userInfo?.userDescription
    }

    fun getAccessToken(): String {
        return sharedPref[ACCESS_TOKEN] ?: EMPTY_STRING
    }

    fun getUserId(): Int {
        return sharedPref[USER_ID] ?: EMPTY_INT
    }

    fun getUsername(): String {
        return sharedPref[USERNAME] ?: EMPTY_STRING
    }

    fun getFirstName(): String {
        return sharedPref[FIRST_NAME] ?: EMPTY_STRING
    }

    fun getLastName(): String {
        return sharedPref[LAST_NAME] ?: EMPTY_STRING
    }

    fun getUserDescription(): String {
        return sharedPref[USER_DESCRIPTION] ?: EMPTY_STRING
    }
}








