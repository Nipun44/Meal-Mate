package com.example.mealmate


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var email: TextView
    private lateinit var password: TextView
    private lateinit var notAccount: TextView
    private lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        notAccount = findViewById(R.id.not_have_account_text)
        btnLogin = findViewById(R.id.login_screen_btn)
        email = findViewById(R.id.username)
        password = findViewById(R.id.password)

        auth = FirebaseAuth.getInstance()

        notAccount.setOnClickListener {
            val intent = Intent(this,Register::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {

            val sharedPref = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            if (checking()) {
                val email = email.text.toString()
                val password = password.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val editor = sharedPref.edit()
                            editor.putString("email", email)
                            editor.apply()

                            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainDashboard::class.java)
                           intent.putExtra("email",email)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun checking(): Boolean {
        if (email.text.toString().trim { it <= ' ' }.isNotEmpty() && password.text.toString()
                .trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
}