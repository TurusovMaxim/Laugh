package com.example.laugh.login

import studio.carbonylgroup.textfieldboxes.ExtendedEditText

class LoginPresenter(loginContractView: LoginActivity, loginInteractor: LoginInteractor):
        LoginInteractor.OnLoginContractView {

    private val lgnContractView = loginContractView
    private val lgnInteractor = loginInteractor

    override fun isStaticEmptyLI(): Boolean {
        return lgnContractView.isStaticEmpty()
    }

    override fun logInSuccessLI() {
        lgnContractView.logInSuccess()
    }

    override fun logInFailureLI() {
        lgnContractView.logInFailure()
    }

    fun staticValidate(userPasswordEdit:ExtendedEditText) {
        lgnInteractor.logIn(userPasswordEdit, this)
    }


}