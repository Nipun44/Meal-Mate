package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var notAccount : TextView
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        notAccount = findViewById(R.id.not_have_account_text)
        btnLogin= findViewById(R.id.login_screen_btn)


        notAccount.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this,MealDashboard::class.java)
            startActivity(intent)

        }
    }
}