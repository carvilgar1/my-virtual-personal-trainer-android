package com.carvilgar.myvirtualpersonaltrainer.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.sign_up.SignUpActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.welcome_sign_in_button)
        button.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val button2 = findViewById<Button>(R.id.welcome_sign_up_button)
        button2.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}