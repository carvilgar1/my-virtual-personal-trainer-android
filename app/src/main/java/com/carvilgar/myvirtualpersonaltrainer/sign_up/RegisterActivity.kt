package com.carvilgar.myvirtualpersonaltrainer.sign_up

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carvilgar.myvirtualpersonaltrainer.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameEditText = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        val registerButton: Button = findViewById(R.id.saveProfileButton)

        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // Simula el envío de datos a una API REST
        // Aquí debes reemplazarlo por tu lógica de llamada a la API real.
        FakeApi.register(username, email, password) { success ->
            if (success) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Registration failed, try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

object FakeApi {
    fun register(username: String, email: String, password: String, callback: (Boolean) -> Unit) {
        if (username.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
            callback(true)
        } else {
            callback(false)
        }
    }
}
