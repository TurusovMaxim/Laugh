package com.example.laugh.login

import android.os.Handler
import android.text.Editable
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

private const val LOWER_LIMIT_PWD = 6
private const val UPPER_LIMIT_PWD = 16

class LoginInteractor(private val userLoginEdit: ExtendedEditText,
                      private var userPasswordEdit: ExtendedEditText) {


    private var isPasswordShow: Boolean = false

    private fun areFieldsEmptyOnEdit(text: CharSequence?): Boolean {
        return text.toString().isEmpty()

    }

    private fun passwordIconEnable(
        text: CharSequence?,
        view: LoginView
    ) {
        if (isPasswordShow) {
            view.showPassword()
        } else {
            view.hidePassword()
        }
        setCursorAtRightPlace(text)
    }

    private fun setCursorAtRightPlace(
            text: CharSequence?
    ) {
        userPasswordEdit.setSelection(text.toString().length)
    }

    fun pwdIconEnableDisable(
        text: CharSequence?,
        view: LoginView
    ) {
        if (areFieldsEmptyOnEdit(text)) {
            view.passwordIconDisable()
        } else {
            passwordIconEnable(text, view)
        }
    }


    fun iconShowHidePwd(
        text: CharSequence?,
        view: LoginView
    ) {
        if (!areFieldsEmptyOnEdit(text)) {
            isPasswordShow = !isPasswordShow
            passwordIconEnable(text, view)
        }
    }


    fun isPwdValid(
        text: CharSequence?,
        view: LoginView
    ) {
        if (!areFieldsEmptyOnEdit(text)) {
            if (text.toString().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1)
                view.setSuitableRangeMessage()
            else {
                when(text.toString().length) {
                    in 1 until LOWER_LIMIT_PWD ->
                        view.setLowLimitMessage(LOWER_LIMIT_PWD)
                    in UPPER_LIMIT_PWD + 1 until Int.MAX_VALUE ->
                        view.setUpLimitMessage(UPPER_LIMIT_PWD)
                }
            }
        }
    }


    fun areFieldsEmptyAfterEdit(editable: Editable?): Boolean {
        return editable.toString().isEmpty()
    }


    private fun isStaticEmpty(
            view: LoginView
    ): Boolean {
        return if (userLoginEdit.text.toString().isEmpty()
                && userPasswordEdit.text.toString().isEmpty()) {
            view.setLgnErrorMessage()
            view.setPwdErrorMessage()
            true
        } else {
            false
        }
    }

    private fun isItInRightRange(): Boolean {
        return userPasswordEdit.text.toString().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1
    }

    fun login(
            view: LoginView
    ) {
        if (!isStaticEmpty(view) && isItInRightRange()) {
            Handler().postDelayed({
                view.loginSuccess()
            },2000)
        } else {
            view.loginFailure()
        }
    }
}