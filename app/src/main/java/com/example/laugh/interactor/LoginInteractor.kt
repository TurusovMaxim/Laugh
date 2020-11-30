package com.example.laugh.interactor

import android.content.Context
import android.text.Editable
import com.example.laugh.data.network.request.AuthRequest
import com.example.laugh.data.network.response.UserInfoResponse
import com.example.laugh.data.retofit.ServiceBuilder
import com.example.laugh.data.storage.UserStorage
import com.example.laugh.view.LoginView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

private const val LOWER_LIMIT_PWD = 6
private const val UPPER_LIMIT_PWD = 16

class LoginInteractor (
        private val context: Context,
        private val userLoginEdit: ExtendedEditText,
        private var userPasswordEdit: ExtendedEditText) {

    //network
    private val getUserApi = ServiceBuilder.getApi()

    //storage
    private val userStorage = UserStorage(context)

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



    private fun staticEmptyLogin(view: LoginView): Boolean {
        return if (userLoginEdit.text.toString().isEmpty()) {
            view.setLgnErrorMessage()
            false
        } else {
            true
        }
    }

    private fun staticEmptyPdw(view: LoginView): Boolean {
        return if (userPasswordEdit.text.toString().isEmpty()) {
            view.setPwdErrorMessage()
            false
        } else {
            true
        }
    }




    private fun isItInRightRange(): Boolean {
        return userPasswordEdit.text.toString().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1
    }



    fun login(view: LoginView) {
        if (staticEmptyLogin(view) && staticEmptyPdw(view) && isItInRightRange()
        ) {
            getUserApi?.logIn(AuthRequest(
                    userLoginEdit.text.toString(),
                    userPasswordEdit.text.toString()
            ))?.enqueue(object : Callback<UserInfoResponse> {

                override fun onResponse(
                        call: Call<UserInfoResponse>,
                        response: Response<UserInfoResponse>
                ) {
                    val userResponse:UserInfoResponse? = response.body()
                    if(userResponse != null) {
                        userStorage.setUserData(userResponse)
                    }
                    view.loginSuccess()
                }

                override fun onFailure(
                        call: Call<UserInfoResponse>,
                        t: Throwable
                ) {
                    view.loginFailure()
                }
            })
        }
    }
}
