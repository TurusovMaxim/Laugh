package com.example.laugh.login

interface LoginContractInteractor {
    fun logInSuccessCI()
    fun logInFailureCI()
    fun setLgnErrorMessage()
    fun setPwdErrorMessage()
    fun passwordIconDisable()
    fun showPassword()
    fun hidePassword()
    fun setSuitableRangeMessage()
    fun setLowLimitMessage(LOWER_LIMIT_PWD: Int)
    fun setUpLimitMessage(UPPER_LIMIT_PWD: Int)
}