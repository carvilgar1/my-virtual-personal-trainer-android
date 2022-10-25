package com.carvilgar.myvirtualpersonaltrainer.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.carvilgar.myvirtualpersonaltrainer.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val button = findViewById<Button>(R.id.btn_sign_up)
        button.setOnClickListener{
            setContentView(R.layout.index_layout)
        }
    }
}