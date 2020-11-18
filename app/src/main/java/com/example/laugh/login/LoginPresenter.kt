package com.example.laugh.login

import studio.carbonylgroup.textfieldboxes.ExtendedEditText

class LoginPresenter(loginContractView: LoginActivity, loginInteractor: LoginInteractor):
        LoginContractInteractor {

    private val lgnContractView = loginContractView
    private val lgnInteractor = loginInteractor

    override fun isStaticEmptyCI(): Boolean {
        return lgnContractView.isStaticEmptyCI()
    }

    override fun logInSuccessCI() {
        lgnContractView.logInSuccessCI()
    }

    override fun logInFailureCI() {
        lgnContractView.logInFailureCI()
    }

    fun staticValidate(userPasswordEdit:ExtendedEditText) {
        lgnInteractor.logIn(userPasswordEdit, this)
    }


}