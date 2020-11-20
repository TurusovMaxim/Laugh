package com.example.laugh.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.laugh.MainActivity
import com.example.laugh.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes


class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var userLogin:TextFieldBoxes
    private lateinit var userPassword:TextFieldBoxes

    private lateinit var userLoginEdit:ExtendedEditText
    private lateinit var userPasswordEdit:ExtendedEditText

    private lateinit var loginButton:Button

    private lateinit var progressBar:ProgressBar

    private lateinit var loginPresenter: LoginPresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        initView()

        loginPresenter = LoginPresenter(
                this,
                LoginInteractor(userLoginEdit, userPasswordEdit))

        //set a password icon-eye and check a password validation
        userPasswordEdit.doOnTextChanged { text, _, _, _ ->
            pwdIconEnableDisable(text)
            isPwdValid(text)
            userPassword.endIconImageButton.setOnClickListener {iconShowHidePwd(text)}
        }

        userLoginEdit.doAfterTextChanged{
            if(areFieldsEmptyAfterEdit(it))
                setLgnErrorMessage()
        }

        userPasswordEdit.doAfterTextChanged {
            if(areFieldsEmptyAfterEdit(it))
                setPwdErrorMessage()
        }

        loginButton.setOnClickListener { login() }

    }

    private fun initView() {
        userLogin = findViewById(R.id.login_txt)
        userPassword = findViewById(R.id.password_txt)

        userLoginEdit = findViewById(R.id.login_edit)
        userPasswordEdit = findViewById(R.id.password_edit)

        progressBar = findViewById(R.id.progress_login)

        loginButton = findViewById(R.id.login_btn)
    }

    private fun lowLimitMessage(LOWER_LIMIT_PWD: Int): String
            = getString(R.string.lower_limit_password, LOWER_LIMIT_PWD)

    private fun upLimitMessage(UPPER_LIMIT_PWD: Int): String
            = getString(R.string.upper_limit_password, UPPER_LIMIT_PWD)

    private fun errorMessage(): String = getString(R.string.empty_field)

    private fun login() {
        loginPresenter.staticEmptyValidate()
    }

    private fun pwdIconEnableDisable(text: CharSequence?) {
        loginPresenter.pwdIconShowHide(text)
    }

    private fun iconShowHidePwd(text: CharSequence?) {
        loginPresenter.pwdIconChange(text)
    }

    private fun isPwdValid(text: CharSequence?) {
        loginPresenter.isPwdValidOnEdit(text)
    }

    override fun showPassword() {
        userPassword.setEndIcon(R.drawable.ic_eye)
        userPasswordEdit.transformationMethod = HideReturnsTransformationMethod.getInstance()
    }

    override fun hidePassword() {
        userPassword.setEndIcon(R.drawable.ic_eye_close)
        userPasswordEdit.transformationMethod = PasswordTransformationMethod.getInstance()
    }

    override fun passwordIconDisable() {
        userPassword.removeEndIcon()
    }

    private fun areFieldsEmptyAfterEdit(editable: Editable?): Boolean{
        return loginPresenter.emptyValidateAfterEdit(editable)
    }

    override fun setLgnErrorMessage() {
        userLogin.setError(errorMessage(), false)
    }

    override fun setPwdErrorMessage() {
        userPassword.setError(errorMessage(), false)
    }

    override fun setSuitableRangeMessage() {
        userPassword.helperText = ""
    }

    override fun setLowLimitMessage(LOWER_LIMIT_PWD: Int) {
        userPassword.helperText = lowLimitMessage(LOWER_LIMIT_PWD)
    }

    override fun setUpLimitMessage(UPPER_LIMIT_PWD: Int) {
        userPassword.helperText = upLimitMessage(UPPER_LIMIT_PWD)
    }

    private fun showProgressBar() {
        loginButton.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        loginButton.visibility = View.VISIBLE
    }

    private fun showSnackbar() {
        //get root view from current activity
        val view = android.R.id.content
        val snackbar = Snackbar.make(findViewById(view), R.string.snackbar_error, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.bittersweet))
        snackbar.show()
    }

    override fun loginSuccess() {
        showProgressBar()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun loginFailure() {
        hideProgressBar()
        showSnackbar()
    }
}
