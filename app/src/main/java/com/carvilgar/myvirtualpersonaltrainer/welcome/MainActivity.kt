package com.carvilgar.myvirtualpersonaltrainer.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.carvilgar.myvirtualpersonaltrainer.R
import com.carvilgar.myvirtualpersonaltrainer.singin.SingInActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a button that goes to the sing in activity
        val button = findViewById<Button>(R.id.welcome_sign_in_button)
        button.setOnClickListener{
            val intent = Intent(this, SingInActivity::class.java)
            startActivity(intent)
        }
    }
}