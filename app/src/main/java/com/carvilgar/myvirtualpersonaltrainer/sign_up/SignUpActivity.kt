package com.carvilgar.myvirtualpersonaltrainer.sign_up

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.carvilgar.myvirtualpersonaltrainer.R


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initializeSpinner()

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

    private fun initializeSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner_activity_level_sign_up)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        resources.getStringArray(R.array.activity_level_options))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

    }
}