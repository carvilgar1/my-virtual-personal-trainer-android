package com.carvilgar.myvirtualpersonaltrainer.sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.validation.ValidationError


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signIn(view: View) {
        val emailEditText = findViewById<EditText>(R.id.et_email_sign_in)
        val passwordEditText = findViewById<EditText>(R.id.et_pass_sign_in)
        val errors = ValidationError<Any?>()

        if (SignInLogic().signUpInputTextValidation(emailEditText.text.toString(),
                passwordEditText.text.toString(), errors)) {
            setContentView(R.layout.index_layout)
        } else {
            //Email errors
            if (errors.getErrors("email") != null) {
                when (errors.getErrors("email")) {
                    LoginErrors.EMPTY_FIELD -> {
                        emailEditText.error = resources.getString(R.string.invalid_email)
                    }
                    LoginErrors.NOT_VALID_FIELD-> {
                        emailEditText.error = resources.getString(R.string.invalid_format_email)
                    }
                }
            }

            //Password errors
            if (errors.getErrors("password") != null) {
                when (errors.getErrors("password")) {
                    LoginErrors.EMPTY_FIELD -> {
                        passwordEditText.error = resources.getString(R.string.invalid_pass)
                    }
                    LoginErrors.NOT_VALID_FIELD-> {
                        passwordEditText.error = resources.getString(R.string.weak_pass)
                    }
                }
            }
        }
    }
}

