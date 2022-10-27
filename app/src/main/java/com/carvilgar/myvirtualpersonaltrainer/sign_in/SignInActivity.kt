package com.carvilgar.myvirtualpersonaltrainer.sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.user.AppAuthenticationManagerValidator


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signIn(view: View) {
        val email = findViewById<EditText>(R.id.et_email_sign_in)
        val password = findViewById<EditText>(R.id.et_pass_sign_in)

        if (signUpInputTextValidation(email, password)) setContentView(R.layout.index_layout)
    }

    private fun signUpInputTextValidation(emailEditText: EditText, passwordEditText: EditText) : Boolean {
        return emailEditTextIsValid(emailEditText).and(passwordEditTextIsValid(passwordEditText))
    }

    private fun emailEditTextIsValid(emailEditText: EditText) : Boolean {
        val email = emailEditText.text.toString()
        return if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)
                    && AppAuthenticationManagerValidator().checkEmailIsValid(email)) {
            true
        } else if (AppAuthenticationManagerValidator().checkEmailIsNotEmptyOrNull(email)){
            emailEditText.error = resources.getString(R.string.invalid_format_email)
            false
        }else {
            emailEditText.error = resources.getString(R.string.invalid_email)
            false
        }
    }

    private fun passwordEditTextIsValid(passwordEditText: EditText) : Boolean {
        val password = passwordEditText.text.toString()
        return if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)
                    && AppAuthenticationManagerValidator().checkPasswordIsValid(password)) {
            true
        } else if (AppAuthenticationManagerValidator().checkPassIsNotEmptyOrNull(password)) {
            passwordEditText.error = resources.getString(R.string.weak_pass)
            false
        } else {
            passwordEditText.error = resources.getString(R.string.invalid_pass)
            false
        }
    }
}

