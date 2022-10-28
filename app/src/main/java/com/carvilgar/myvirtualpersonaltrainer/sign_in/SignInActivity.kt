package com.carvilgar.myvirtualpersonaltrainer.sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.carvilgar.myvirtualpersonaltrainer.R


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signIn(view: View) {
        val emailEditText = findViewById<EditText>(R.id.et_email_sign_in)
        val passwordEditText = findViewById<EditText>(R.id.et_pass_sign_in)

        if (SignInLogic().signUpInputTextValidation(emailEditText.text.toString(),
                passwordEditText.text.toString())) {
            setContentView(R.layout.index_layout)
        } else {
            emailEditText.error = "Email is not valid"
            passwordEditText.error = "Password is not valid"
        }
    }
}

