package com.example.laugh.view

interface LoginView {
    fun loginSuccess()
    fun loginFailure()
    fun setLgnErrorMessage()
    fun setPwdErrorMessage()
    fun passwordIconDisable()
    fun showPassword()
    fun hidePassword()
    fun setSuitableRangeMessage()
    fun setLowLimitMessage(LOWER_LIMIT_PWD: Int)
    fun setUpLimitMessage(UPPER_LIMIT_PWD: Int)
}