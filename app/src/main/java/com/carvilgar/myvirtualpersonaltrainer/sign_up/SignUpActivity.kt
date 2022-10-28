package com.carvilgar.myvirtualpersonaltrainer.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.carvilgar.myvirtualpersonaltrainer.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val button = findViewById<Button>(R.id.btn_sign_up)
        button.setOnClickListener{
            signUp()
        }
    }

    private fun signUp() {
        val name = findViewById<EditText>(R.id.et_name_sign_up)
        val surName = findViewById<EditText>(R.id.et_surname_sign_up)
        val birthDate = findViewById<EditText>(R.id.et_birth_date_sign_up)
        val email = findViewById<EditText>(R.id.et_email_sign_up)
        val password = findViewById<EditText>(R.id.et_pass_sign_up)
        val height = findViewById<EditText>(R.id.et_height_sign_up)
        val weight = findViewById<EditText>(R.id.et_weight_sign_up)


    }
}