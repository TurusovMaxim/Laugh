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


    private val lowerLimit = 6
    private val upperLimit = 16


    private fun didPwdFillCorrectly():Boolean {
        return getEditPassword().length in lowerLimit until upperLimit - 1
    }

    private fun isPwdValid() {
        val lowLimitMessage: String = getString(R.string.lower_limit_password, lowerLimit)
        val upLimitMessage: String = getString(R.string.upper_limit_password, upperLimit)


        userPasswordEdit.doOnTextChanged { text, _, _, _ ->
            text?.let {
                if (it.isNotEmpty() && didPwdFillCorrectly()) {
                    userPassword.helperText = ""
                } else {
                    when(getEditPassword().length) {
                        in 1 until lowerLimit ->
                            userPassword.helperText = lowLimitMessage
                        in upperLimit + 1 until Int.MAX_VALUE ->
                            userPassword.helperText = upLimitMessage
                    }
                }
            }
        }
    }

    private fun errorMessage(): String = getString(R.string.empty_field)

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
