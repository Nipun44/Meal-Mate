package com.example.mealmate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Profile : AppCompatActivity() {
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var tvUserContact: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvUserName = findViewById(R.id.tvUserName)
        tvUserEmail = findViewById(R.id.tvUserEmail)
        tvUserContact = findViewById(R.id.tvUserContact)

        // Retrieve the shared preferences object
        val sharedPref = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

// Retrieve the user details from SharedPreferences
        val name = sharedPref.getString("Name", "").toString()
        val email = sharedPref.getString("Email", "").toString()
        val contact = sharedPref.getString("Contact", "").toString()
// retrieve other user details as needed


        tvUserName.text = name
        tvUserEmail.text = email
        tvUserContact.text = contact
    }
}