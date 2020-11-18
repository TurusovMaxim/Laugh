package com.example.laugh

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
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

class LoginActivity : AppCompatActivity() {

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

        initView()

        userPasswordEdit.doOnTextChanged { text, _, _, _ ->
            if (isPwdEmptyOn(text)) {
                passwordIconDisable()
            }
            else {
                passwordIconEnable(text)
                isPasswordValid(text)
                userPassword.endIconImageButton.setOnClickListener {
                    isPasswordShow = !isPasswordShow
                    passwordIconEnable(text)
                }
            }
        }

        userLoginEdit.doAfterTextChanged{
            if (areFieldsEmptyAfterEdit(it)) setLgnErrorMessage()
        }

        userPasswordEdit.doAfterTextChanged {
            if (areFieldsEmptyAfterEdit(it)) setPwdErrorMessage()
        }

        loginButton.setOnClickListener {
            if (!isStaticEmpty() && inRightRange()) {
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

    private fun isStaticEmpty(): Boolean {
        return when {
            getEditLogin().isEmpty() -> {
                setLgnErrorMessage()
                true
            }
            getEditPassword().isEmpty() -> {
                setPwdErrorMessage()
                true
            }
            else -> {
                false
            }
        }
    }

    private fun inRightRange(): Boolean {
        return getEditPassword().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1
    }

    private fun isPasswordValid(text: CharSequence?) {
        if (text.toString().length in LOWER_LIMIT_PWD until UPPER_LIMIT_PWD + 1)
            setSuitableRangeMessage()
        else {
            when(text.toString().length) {
                in 1 until LOWER_LIMIT_PWD ->
                    setLowLimitMessage()
                in UPPER_LIMIT_PWD + 1 until Int.MAX_VALUE ->
                    setUpLimitMessage()
            }
        }
    }

    private fun initView() {
        userLogin = findViewById(R.id.login_txt)
        userPassword = findViewById(R.id.password_txt)

        userLoginEdit = findViewById(R.id.login_edit)
        userPasswordEdit = findViewById(R.id.password_edit)

        progressBar = findViewById(R.id.progress_login)

        loginButton = findViewById(R.id.login_btn)
    }

    private fun getEditLogin():String = userLoginEdit.text.toString()
    private fun getEditPassword():String = userPasswordEdit.text.toString()

    private fun lowLimitMessage(): String = getString(R.string.lower_limit_password, LOWER_LIMIT_PWD)
    private fun upLimitMessage(): String = getString(R.string.upper_limit_password, UPPER_LIMIT_PWD)

    private fun errorMessage(): String = getString(R.string.empty_field)

    private fun passwordIconEnable(text: CharSequence?) {
        if (isPasswordShow) {
            showPassword()
        } else {
            hidePassword()
        }
        setCursorAtRightPlace(text)
    }

    private fun showPassword() {
        userPassword.setEndIcon(R.drawable.ic_eye)
        userPasswordEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    private fun hidePassword() {
        userPassword.setEndIcon(R.drawable.ic_eye_close)
        userPasswordEdit.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    private fun setCursorAtRightPlace(text: CharSequence?) {
        userPasswordEdit.setSelection(text.toString().length)
    }

    private fun passwordIconDisable() {
        userPassword.removeEndIcon()
    }

    private fun isPwdEmptyOn(text: CharSequence?): Boolean {
        return text.toString().isEmpty()
    }

    private fun areFieldsEmptyAfterEdit(editable: Editable?): Boolean {
        return editable.toString().isEmpty()
    }

    private fun setPwdErrorMessage() {
        userPassword.setError(errorMessage(), false)
    }

    private fun setLgnErrorMessage() {
        userLogin.setError(errorMessage(), false)
    }

    private fun setSuitableRangeMessage() {
        userPassword.helperText = ""
    }

    private fun setLowLimitMessage() {
        userPassword.helperText = lowLimitMessage()
    }

    private fun setUpLimitMessage() {
        userPassword.helperText = upLimitMessage()
    }

    private fun showProgressBar() {
        loginButton.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        loginButton.visibility = View.VISIBLE
    }
}
