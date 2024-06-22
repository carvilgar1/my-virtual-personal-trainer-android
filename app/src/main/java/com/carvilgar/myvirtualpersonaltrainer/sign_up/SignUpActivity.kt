package com.carvilgar.myvirtualpersonaltrainer.sign_up

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.authentication.*
import com.carvilgar.myvirtualpersonaltrainer.user.UserDataActivity
import com.carvilgar.validation.ValidationError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class SignUpActivity : AppCompatActivity() {
    private var authenticationManager = AuthenticationManager()
    private val userCredentialsValidator by lazy { UserCredentialsValidator() }
    private val usernameEditText by lazy { findViewById<EditText>(R.id.username) }
    private val emailEditText by lazy { findViewById<EditText>(R.id.email) }
    private val passwordEditText by lazy { findViewById<EditText>(R.id.password) }
    private val confirmedPasswordEditText by lazy { findViewById<EditText>(R.id.confirm_password) }
    private val registerButton by lazy { findViewById<Button>(R.id.saveProfileButton) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        registerButton.setOnClickListener { registerUser() }
    }

    private fun registerUser() {
        val userCredentials = UserCredentials(
            username = usernameEditText.text.toString(),
            email = emailEditText.text.toString(),
            password = passwordEditText.text.toString(),
            confirmedPassword = confirmedPasswordEditText.text.toString()
        )

        val errors = ValidationError<Any?>()
        if (userCredentialsValidator.validate(userCredentials, errors)) {
            performNetworkRequest(userCredentials)
        } else {
            addUsernameError(errors)
            addEmailErrors(errors)
            addPasswordErrors(errors)
        }
    }

    private fun performNetworkRequest(userCredentials: UserCredentials) {
        val client = OkHttpClient()
        val body = MultipartBody.Builder().setType(MultipartBody.FORM)
            .apply {
                addFormDataPart("username", userCredentials.username)
                addFormDataPart("email", userCredentials.email)
                addFormDataPart("password", userCredentials.password)
            }
            .build()
        val request = Request.Builder()
            .url("http://10.0.2.2:5000/sign_up?lang=en")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { Toast.makeText(this@SignUpActivity, "Network error", Toast.LENGTH_SHORT).show() }
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    if (response.isSuccessful) {
                        authenticationManager.retrieveAutToken(userCredentials, object:  AuthTokenCallback {
                            @RequiresApi(Build.VERSION_CODES.O)
                            override fun onSuccess(authTokenResponse: AuthTokenResponse) {
                                runOnUiThread {
                                    authenticationManager.saveTokenToPreferences(authTokenResponse, this@SignUpActivity)
                                }
                            }

                            override fun onError(errorMessage: String) {
                                runOnUiThread {
                                    Toast.makeText(this@SignUpActivity, errorMessage, Toast.LENGTH_LONG).show()
                                }
                            }
                        })

                        val intent = Intent(this@SignUpActivity, UserDataActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        mapRestServiceErrors(response)
                    }
                }
            }
        })
    }

    private fun mapRestServiceErrors(response: Response) {
        if (response.code == 400) {
            val responseBody = response.body?.string() ?: throw IOException("Empty response body")
            val gson = Gson()
            val type = object : TypeToken<SignUpResponse>() {}.type
            val signUpResponse: SignUpResponse = gson.fromJson(responseBody, type)

            if (signUpResponse.errors?.username?.firstOrNull() != null) {
                val usernameError = signUpResponse.errors.username.firstOrNull()
                usernameEditText.error = usernameError
            }
            if (signUpResponse.errors?.email?.firstOrNull() != null) {
                val emailError = signUpResponse.errors.email.firstOrNull()
                emailEditText.error = emailError
            }

            Toast.makeText(
                this@SignUpActivity,
                "Error registering: Bad Request",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                this@SignUpActivity,
                "Error registering: ${response.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun addPasswordErrors(errors: ValidationError<Any?>) {
        if (errors.getErrors("password") != null) {
            when (errors.getErrors("password")) {
                AuthenticationErrors.EMPTY_FIELD -> {
                    passwordEditText.error = resources.getString(R.string.invalid_pass)
                }
                AuthenticationErrors.NOT_VALID_FIELD -> {
                    passwordEditText.error = resources.getString(R.string.weak_pass)
                }
                AuthenticationErrors.PASSWORDS_NOT_MATCH -> {
                    confirmedPasswordEditText.error = resources.getString(R.string.pass_dont_match)
                }
            }
        }
    }

    private fun addEmailErrors(errors: ValidationError<Any?>) {
        if (errors.getErrors("email") != null) {
            when (errors.getErrors("email")) {
                AuthenticationErrors.EMPTY_FIELD -> {
                    emailEditText.error = resources.getString(R.string.invalid_email)
                }
                AuthenticationErrors.NOT_VALID_FIELD -> {
                    emailEditText.error = resources.getString(R.string.invalid_format_email)
                }
            }
        }
    }

    private fun addUsernameError(errors: ValidationError<Any?>) {
        if (errors.getErrors("username") != null) {
            when (errors.getErrors("username")) {
                AuthenticationErrors.EMPTY_FIELD -> {
                    usernameEditText.error = resources.getString(R.string.invalid_email)
                }
            }
        }
    }
}
