package com.example.laugh.login

interface LoginContractView {
    fun isStaticEmpty(): Boolean
    fun logInSuccess()
    fun logInFailure()
}