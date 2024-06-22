package com.carvilgar.myvirtualpersonaltrainer.user

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.authentication.AuthTokenCallback
import com.carvilgar.myvirtualpersonaltrainer.authentication.AuthTokenResponse
import com.carvilgar.myvirtualpersonaltrainer.authentication.AuthenticationManager
import com.carvilgar.validation.ValidationError
import okhttp3.*
import java.io.IOException

class UserDataActivity : AppCompatActivity() {
    private val authenticationManager = AuthenticationManager()
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var activityLevelSpinner: Spinner
    private lateinit var maxHeartRateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_data)

        initializeViews()
    }

    private fun initializeViews() {
        nameEditText = findViewById(R.id.name)
        surnameEditText = findViewById(R.id.surname)
        ageEditText = findViewById(R.id.age)
        weightEditText = findViewById(R.id.weight)
        heightEditText = findViewById(R.id.height)
        activityLevelSpinner = findViewById(R.id.activityLevel)
        maxHeartRateEditText = findViewById(R.id.maxHeartRate)

        val saveProfileButton: Button = findViewById(R.id.registerButton)
        saveProfileButton.setOnClickListener {
            saveProfile()
        }
    }

    /*
    private fun showCarouselDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_carousel, null)
        val viewPager = dialogView.findViewById<ViewPager2>(R.id.viewPagerCarousel)
        val carouselTexts = listOf(
            "Ahora vamos a ayudarle a configurar su perfil...",
            "Por su seguridad, consulte antes con su médico..."
        )
        viewPager.adapter = CarouselAdapter(carouselTexts)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
                finish()  // Finalizar actividad si el usuario no acepta
            }
            .show()
    }
    */

    private fun saveProfile() {
        val userInputData = UserInputData(
            nameEditText.text.toString(),
            surnameEditText.text.toString(),
            ageEditText.text.toString(),
            heightEditText.text.toString(),
            weightEditText.text.toString(),
            activityLevelSpinner.selectedItem.toString().toInt(),
            maxHeartRateEditText.text.toString()?.toInt()
        )

        val errors = ValidationError<Any?>()
        val userInputDataValidator = UserInputDataValidator()

        if (userInputDataValidator.validate(userInputData, errors)) {
            val client = OkHttpClient()
            val body = MultipartBody.Builder().setType(MultipartBody.FORM)
                .apply {
                    addFormDataPart("name", userInputData.name)
                    addFormDataPart("surname", userInputData.surName)
                    addFormDataPart("age", userInputData.age)
                    addFormDataPart("weight", userInputData.weight)
                    addFormDataPart("height", userInputData.height)
                    addFormDataPart("activityLevel", ActivityLevel.)
                    addFormDataPart("maxHeartRate", userInputData.password)
                }
                .build()
            val request = Request.Builder()
                .url("http://10.0.2.2:5000/sign_up?lang=en")
                .post(body)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread { Toast.makeText(this@UserDataActivity, "Network error", Toast.LENGTH_SHORT).show() }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        if (response.isSuccessful) {
                            authenticationManager.retrieveAutToken(userCredentials, object:
                                AuthTokenCallback {
                                @RequiresApi(Build.VERSION_CODES.O)
                                override fun onSuccess(authTokenResponse: AuthTokenResponse) {
                                    runOnUiThread {
                                        authenticationManager.saveTokenToPreferences(authTokenResponse, this@UserDataActivity)
                                    }
                                }

                                override fun onError(errorMessage: String) {
                                    runOnUiThread {
                                        Toast.makeText(this@UserDataActivity, errorMessage, Toast.LENGTH_LONG).show()
                                    }
                                }
                            })

                            val intent = Intent(this@UserDataActivity, UserDataActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@UserDataActivity,
                                "Unexpected error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        } else {
            addFieldErrors("name", nameEditText, errors)
            addFieldErrors("surName", surnameEditText, errors)
            addFieldErrors("height", heightEditText, errors)
            addFieldErrors("weight", weightEditText, errors)
        }
    }

    private fun addFieldErrors(fieldName: String, editText: EditText, errors: ValidationError<Any?>) {
        errors.getErrors(fieldName)?.let { _ ->
            editText.error = resources.getString(when (fieldName) {
                "name" -> R.string.invalid_name
                "surName" -> R.string.invalid_surname
                "age" -> R.string.invalid_birth_date
                "height" -> R.string.invalid_height
                "weight" -> R.string.invalid_weight
                else -> R.string.invalid_name
            })
        }
    }
}
