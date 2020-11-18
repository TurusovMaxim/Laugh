package com.example.laugh.login

import android.content.Context
import android.os.Handler
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

/*private const val LOWER_LIMIT_PWD = 6
private const val UPPER_LIMIT_PWD = 16*/

class LoginInteractor(context: Context) {



    private fun inRightRange(userPasswordEdit: ExtendedEditText): Boolean {
        return userPasswordEdit.text.toString().length in 6 until 16 + 1
    }

    fun logIn(userPasswordEdit: ExtendedEditText, view: LoginContractInteractor) {
            if (!view.isStaticEmptyCI() && inRightRange(userPasswordEdit)) {
                Handler().postDelayed({
                    view.logInSuccessCI()
                },2000)
            } else {
                view.logInFailureCI()
            }
    }
}