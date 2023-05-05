package com.example.mealmate

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.FirebaseFirestore

class MealDashboard : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    private lateinit var iHaveExtraBtn: Button
    private lateinit var doYouHaveExtraBtn: Button
    private lateinit var btnLogout: Button
    private lateinit var btnUserProfile: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_dashboard)

        iHaveExtraBtn = findViewById(R.id.iHaveExtraBtn)
        doYouHaveExtraBtn = findViewById(R.id.doYouHaveExtraBtn)
        btnLogout = findViewById(R.id.btnLogout)
        btnUserProfile = findViewById(R.id.btnUserProfile)

        iHaveExtraBtn.setOnClickListener {
            val intent = Intent(this, GivePartFood::class.java)
            startActivity(intent)
        }

        doYouHaveExtraBtn.setOnClickListener {
            val intent = Intent(this, FetchingFood::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {

        }

        btnUserProfile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        val sharedPref = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getString("email", "1")
        val email = intent.getStringExtra("email")


        if (isLogin == "1") {

            // Get a reference to the Firestore database
            db = FirebaseFirestore.getInstance()
            // Set the collection name and the field name for email
            val collectionName = "USERS"
            val fieldName = "email"


            // Build the Firestore query to search for the document with matching email
            db.collection(collectionName)
                .whereEqualTo(fieldName, email)
                .get()
                .addOnSuccessListener { documents ->
                    // Check if the query returned any documents
                    if (documents.isEmpty) {
                        // Handle case where no documents match the email
                    } else {
                        // Get the first document returned by the query
                        val document = documents.first()

                        // Retrieve the user data from the document
                        val name = document.getString("name")
                        val contact = document.getString("contact")
                        val password = document.getString("password")

                        // Do something with the user data
                        // For example, update the UI to show the user data
                        with(sharedPref.edit()) {
                            putString("Email", email)
                            putString("Name", name)
                            putString("Contact", contact)
                            putString("Password", password)
                            apply()
                        }
                    }
                }
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}