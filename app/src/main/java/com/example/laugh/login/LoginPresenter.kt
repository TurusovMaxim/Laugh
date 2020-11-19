package com.example.laugh.login

import android.text.Editable
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

class LoginPresenter(loginContractView: LoginActivity, loginInteractor: LoginInteractor):
        LoginContractInteractor {

    private val lgnContractView = loginContractView
    private val lgnInteractor = loginInteractor

    override fun logInSuccessCI() {
        lgnContractView.logInSuccessCI()
    }

    override fun logInFailureCI() {
        lgnContractView.logInFailureCI()
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

    fun staticValidate(userLoginEdit: ExtendedEditText,userPasswordEdit:ExtendedEditText) {
        lgnInteractor.login(userLoginEdit, userPasswordEdit, this)
    }

    fun emptyValidateAfterEdit(editable: Editable?): Boolean {
        return lgnInteractor.areFieldsEmptyAfterEdit(editable)
    }

    fun isPwdIconShow(text: CharSequence?, userPasswordEdit: ExtendedEditText) {
        lgnInteractor.isPwdIconEnableDisable(text, userPasswordEdit, this)
    }

    fun isPasswordIconChange(text: CharSequence?, userPasswordEdit: ExtendedEditText) {
        lgnInteractor.isPwdIconChange(text, userPasswordEdit, this)
    }

    fun isPwdValidOnEdit(text: CharSequence?) {
        lgnInteractor.isPasswordValid(text, this)
    }
}