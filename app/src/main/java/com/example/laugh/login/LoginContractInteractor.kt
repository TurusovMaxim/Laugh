package com.example.laugh.login

interface LoginContractInteractor {
    fun isStaticEmptyCI(): Boolean
    fun logInSuccessCI()
    fun logInFailureCI()
}