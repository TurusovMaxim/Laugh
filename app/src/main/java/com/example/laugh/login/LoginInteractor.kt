package com.example.laugh.login

import android.os.Handler
import android.text.Editable
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

private const val LOWER_LIMIT_PWD = 6
private const val UPPER_LIMIT_PWD = 16

class LoginInteractor {

    private var isPasswordShow: Boolean = false

    private fun areFieldsEmptyOnEdit(text: CharSequence?): Boolean {
        return text.toString().isEmpty()
    }

    private fun passwordIconEnable(
        text: CharSequence?,
        userPasswordEdit: ExtendedEditText,
        view: LoginContractInteractor
    ) {
        if (isPasswordShow) {
            view.showPassword()
        } else {
            view.hidePassword()
        }
        setCursorAtRightPlace(text, userPasswordEdit)
    }

    private fun setCursorAtRightPlace(
        text: CharSequence?,
        userPasswordEdit: ExtendedEditText
    ) {
        userPasswordEdit.setSelection(text.toString().length)
    }

    fun isPwdIconEnableDisable(
        text: CharSequence?,
        userPasswordEdit: ExtendedEditText,
        view: LoginContractInteractor
    ) {
        if (areFieldsEmptyOnEdit(text)) {
            view.passwordIconDisable()
        } else {
            passwordIconEnable(text, userPasswordEdit, view)
        }
    }

    fun isPwdIconChange(
        text: CharSequence?,
        userPasswordEdit: ExtendedEditText,
        view: LoginContractInteractor
    ) {
        if (!areFieldsEmptyOnEdit(text)) {
            isPasswordShow = !isPasswordShow
            passwordIconEnable(text, userPasswordEdit, view)
        }
    }

    fun isPasswordValid(
        text: CharSequence?,
        view: LoginContractInteractor
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

    private fun isStaticEmptyCI(
            userLoginEdit: ExtendedEditText,
            userPasswordEdit: ExtendedEditText,
            view: LoginContractInteractor
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

    private fun inRightRange(userPasswordEdit: ExtendedEditText): Boolean {
        return userPasswordEdit.text.toString().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1
    }

    fun login(
            userLoginEdit: ExtendedEditText,
            userPasswordEdit: ExtendedEditText,
            view: LoginContractInteractor
    ) {
        if (!isStaticEmptyCI(userLoginEdit, userPasswordEdit, view) && inRightRange(userPasswordEdit)) {
            Handler().postDelayed({
                view.logInSuccessCI()
            },2000)
        } else {
            view.logInFailureCI()
        }
    }
}