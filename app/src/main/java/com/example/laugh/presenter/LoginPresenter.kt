package com.example.laugh.presenter

import android.text.Editable
import com.example.laugh.interactor.LoginInteractor
import com.example.laugh.ui.LoginActivity
import com.example.laugh.view.LoginView

class LoginPresenter(loginContractView: LoginActivity, loginInteractor: LoginInteractor):
        LoginView {

    private val lgnContractView = loginContractView
    private val lgnInteractor = loginInteractor

    override fun loginSuccess() {
        lgnContractView.loginSuccess()
    }

    override fun loginFailure() {
        lgnContractView.loginFailure()
    }

    override fun setLgnErrorMessage() {
        lgnContractView.setLgnErrorMessage()
    }

    override fun setPwdErrorMessage() {
        lgnContractView.setPwdErrorMessage()
    }

    override fun passwordIconDisable() {
        lgnContractView.passwordIconDisable()
    }

    override fun showPassword() {
        lgnContractView.showPassword()
    }

    override fun hidePassword() {
        lgnContractView.hidePassword()
    }

    override fun setSuitableRangeMessage() {
        lgnContractView.setSuitableRangeMessage()
    }

    override fun setLowLimitMessage(LOWER_LIMIT_PWD: Int) {
        lgnContractView.setLowLimitMessage(LOWER_LIMIT_PWD)
    }

    override fun setUpLimitMessage(UPPER_LIMIT_PWD: Int) {
        lgnContractView.setUpLimitMessage(UPPER_LIMIT_PWD)
    }

    fun staticEmptyValidate() {
        lgnInteractor.login(this)
    }

    fun emptyValidateAfterEdit(editable: Editable?): Boolean {
        return lgnInteractor.areFieldsEmptyAfterEdit(editable)
    }

    fun pwdIconShowHide(text: CharSequence?) {
        lgnInteractor.pwdIconEnableDisable(text, this)
    }

    fun pwdIconChange(text: CharSequence?) {
        lgnInteractor.iconShowHidePwd(text,this)
    }

    fun isPwdValidOnEdit(text: CharSequence?) {
        lgnInteractor.isPwdValid(text, this)
    }
}