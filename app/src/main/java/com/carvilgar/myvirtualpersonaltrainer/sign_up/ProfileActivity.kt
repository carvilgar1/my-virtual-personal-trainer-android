package com.carvilgar.myvirtualpersonaltrainer.sign_up

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.carvilgar.myvirtualpersonaltrainer.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var activityLevelSpinner: Spinner
    private lateinit var maxHeartRateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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

    private fun saveProfile() {
        val name = nameEditText.text.toString()
        val surname = surnameEditText.text.toString()
        val age = ageEditText.text.toString().toInt()
        val weight = weightEditText.text.toString().toDouble()
        val height = heightEditText.text.toString().toDouble()
        val activityLevel = activityLevelSpinner.selectedItem.toString()
        val maxHeartRate = maxHeartRateEditText.text.toString().toInt()

        Toast.makeText(this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show()
    }
}
