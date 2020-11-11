package com.example.laugh

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

private const val LOWER_LIMIT_PWD = 6
private const val UPPER_LIMIT_PWD = 16

class AuthorizationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var userLogin:TextFieldBoxes
    private lateinit var userPassword:TextFieldBoxes

    private lateinit var userLoginEdit:ExtendedEditText
    private lateinit var userPasswordEdit:ExtendedEditText

    private lateinit var loginButton:Button

    private lateinit var progressBar:ProgressBar

    private var isPasswordShow: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        userLogin = findViewById(R.id.login_txt)
        userPassword = findViewById(R.id.password_txt)

        userLoginEdit = findViewById(R.id.login_edit)
        userPasswordEdit = findViewById(R.id.password_edit)

        progressBar = findViewById(R.id.progress_login)

        loginButton = findViewById(R.id.login_btn)
        loginButton.setOnClickListener(this)
    }


    override fun onStart() {
        super.onStart()

        isPasswordIconShow()
        isPwdValid()
        isLgnEmpty()
        isPwdEmpty()
    }


    private fun getEditLogin():String = userLoginEdit.text.toString()

    private fun getEditPassword():String = userPasswordEdit.text.toString()

    private fun lowLimitMessage(): String = getString(R.string.lower_limit_password, LOWER_LIMIT_PWD)
    private fun upLimitMessage(): String = getString(R.string.upper_limit_password, UPPER_LIMIT_PWD)

    private fun errorMessage(): String = getString(R.string.empty_field)


    private fun passwordIconEnable() {
        if (isPasswordShow) {
            userPassword.setEndIcon(R.drawable.ic_eye)
            userPasswordEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
        } else {
            userPassword.setEndIcon(R.drawable.ic_eye_close)
            userPasswordEdit.transformationMethod = PasswordTransformationMethod.getInstance()
        }
    }


    private fun isPasswordIconShow() {

        userPasswordEdit.doOnTextChanged { text, _, _, _ ->
            text?.let { it0 ->
                if (it0.toString().isNotEmpty()) {
                    //set the initial password icon
                    passwordIconEnable()

                    //set the listener for the password icon
                    userPassword.endIconImageButton.setOnClickListener { it1 ->
                        it1?.let {
                            //change the password icon
                            isPasswordShow = !isPasswordShow
                            passwordIconEnable()

                            //set the cursor on the right position
                            userPasswordEdit.setSelection(it0.toString().length)
                        }
                    }
                } else {
                    userPassword.removeEndIcon()
                }
            }
        }
    }


    private fun didPwdFillCorrectly():Boolean {
        return getEditPassword().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD - 1
    }


    private fun isPwdValid() {
        userPasswordEdit.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.isNotEmpty() && didPwdFillCorrectly()) {
                    userPassword.helperText = ""
                } else {
                    when(getEditPassword().length) {
                        in 1 until LOWER_LIMIT_PWD ->
                            userPassword.helperText = lowLimitMessage()
                        in UPPER_LIMIT_PWD + 1 until Int.MAX_VALUE ->
                            userPassword.helperText = upLimitMessage()
                    }
                }
            }
        }
    }


    private fun isPwdEmpty() {
        userPasswordEdit.doAfterTextChanged {
            it?.let {
                if (it.isEmpty())
                    userPassword.setError(errorMessage(), false)
            }
        }
    }


    private fun isLgnEmpty(){
        userLoginEdit.doAfterTextChanged {
            it?.let {
                if (it.isEmpty())
                    userLogin.setError(errorMessage(), false)
            }
        }
    }


    private fun setErrorMsgEmptyField(): Boolean {
        if (getEditLogin().isEmpty() &&
            getEditPassword().isEmpty()) {

            userLogin.setError(errorMessage(), false)
            userPassword.setError(errorMessage(), false)
            return true
        }
        return false
    }


    private fun showProgressBar() {
        loginButton.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        loginButton.visibility = View.VISIBLE
    }


    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.login_btn -> {
                if (!setErrorMsgEmptyField() && didPwdFillCorrectly()) {
                    showProgressBar()
                    Handler().postDelayed({
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 1000)
                } else {
                    hideProgressBar()
                }
            }
        }
    }
}
