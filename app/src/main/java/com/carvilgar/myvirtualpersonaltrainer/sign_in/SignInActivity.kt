package com.carvilgar.myvirtualpersonaltrainer.sign_in

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.user.AppAuthenticationManager
import com.carvilgar.myvirtualpersonaltrainer.user.CheckLoginStatus


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    fun signIn(view: View) {

        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()

        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val password = findViewById<EditText>(R.id.et_password).text.toString()

        val signInInstance = AppAuthenticationManager(email, password)

        when (signInInstance.trySignIn()) {
            CheckLoginStatus.OK -> {
                setContentView(R.layout.index_layout)
            }
            CheckLoginStatus.EMAIL_IS_NOT_VALID -> {
                alertDialog.setTitle("Login")
                alertDialog.setMessage("Email is not valid")
                alertDialog.show()
            }
            CheckLoginStatus.PASSWORD_IS_NOT_VALID -> {
                alertDialog.setTitle("Login")
                alertDialog.setMessage("Password is not valid")
                alertDialog.show()
            }
            CheckLoginStatus.PASSWORD_AND_EMAIL_ARE_NOT_VALID -> {
                alertDialog.setTitle("Login")
                alertDialog.setMessage("Password and email are not valid")
                alertDialog.show()
            }
        }
    }
}