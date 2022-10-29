package com.carvilgar.myvirtualpersonaltrainer.sign_up

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.validation.ValidationError


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initializeSpinner()

        //signUp button click listener
        val signUpButton = findViewById<Button>(R.id.btn_sign_up)
        signUpButton.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val name = findViewById<EditText>(R.id.et_name_sign_up)
        val surName = findViewById<EditText>(R.id.et_surname_sign_up)
        val birthDate = findViewById<EditText>(R.id.et_birth_date_sign_up)
        val email = findViewById<EditText>(R.id.et_email_sign_up)
        val password = findViewById<EditText>(R.id.et_pass_sign_up)
        val passwordConfirmation = findViewById<EditText>(R.id.et_cpass_sign_up)
        val height = findViewById<EditText>(R.id.et_height_sign_up)
        val weight = findViewById<EditText>(R.id.et_weight_sign_up)
        val errors = ValidationError<Any?>()

        val isInputDataValid = SingUpLogic().signUpInputTextValidation(
            name.text.toString(),
            surName.text.toString(),
            birthDate.text.toString(),
            email.text.toString(),
            password.text.toString(),
            passwordConfirmation.text.toString(),
            height.text.toString(),
            weight.text.toString(), errors)

        if (isInputDataValid) {
            setContentView(R.layout.index_layout)
        } else {
            //Name errors
            if (errors.getErrors("name") != null) {
                name.error = resources.getString(R.string.invalid_name)
            }

            //Surname errors
            if (errors.getErrors("surName") != null) {
                surName.error = resources.getString(R.string.invalid_surname)
            }

            //Birth date errors
            if (errors.getErrors("birthDate") != null) {
                if (errors.getErrors("birthDate") == SignUpErrors.EMPTY_FIELD) {
                    birthDate.error = resources.getString(R.string.invalid_birth_date)
                }
                else {
                    birthDate.error = resources.getString(R.string.invalid_format_birth_date)
                }
            }

            //Email errors
            if (errors.getErrors("email") != null) {
                if (errors.getErrors("email") == SignUpErrors.EMPTY_FIELD) {
                    email.error = resources.getString(R.string.invalid_email)
                } else {
                    email.error = resources.getString(R.string.invalid_format_email)
                }
            }

            //Password errors
            if (errors.getErrors("password") != null) {
                if (errors.getErrors("password") == SignUpErrors.EMPTY_FIELD) {
                    password.error = resources.getString(R.string.invalid_pass)
                }
                else {
                    password.error = resources.getString(R.string.weak_pass)
                }
            }

            //Password errors
            if (errors.getErrors("passwordConfirmation") != null) {
                passwordConfirmation.error = resources.getString(R.string.pass_dont_match)
            }

            //Weight errors
            if (errors.getErrors("weight") != null) {
                if (errors.getErrors("weight") == SignUpErrors.EMPTY_FIELD) {
                    weight.error = resources.getString(R.string.invalid_weight)
                }
                else {
                    weight.error = resources.getString(R.string.invalid_format_weight)
                }
            }

            //height errors
            if (errors.getErrors("height") != null) {
                if (errors.getErrors("height") == SignUpErrors.EMPTY_FIELD) {
                    height.error = resources.getString(R.string.invalid_height)
                }
                else {
                    height.error = resources.getString(R.string.invalid_format_height)
                }
            }
        }

    }

    private fun initializeSpinner() {
        val spinner = findViewById<Spinner>(R.id.spinner_activity_level_sign_up)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                        resources.getStringArray(R.array.activity_level_options))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}