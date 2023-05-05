package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterAsIndividual : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var haveAccount: TextView
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtContactNo: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        db = FirebaseFirestore.getInstance()

        haveAccount = findViewById(R.id.already_have_account)
        edtName = findViewById(R.id.edtPersonName)
        edtEmail = findViewById(R.id.edtEmail)
        edtContactNo = findViewById(R.id.edtContactNo)
        edtPassword = findViewById(R.id.edtPassword)
        btnSignup = findViewById(R.id.btnSignUpIndReg)
        auth = FirebaseAuth.getInstance()

        haveAccount.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            if (checking()) {
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val name = edtName.text.toString()
                val contact = edtContactNo.text.toString()
                val user = hashMapOf(
                    "name" to name,
                    "contact" to contact,
                    "password" to password,
                    "email" to email
                )
                val users = db.collection("USERS")
                val query = users.whereEqualTo("email", email).get().addOnSuccessListener { it ->
                    if (it.isEmpty) {
                        auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Sign up success", Toast.LENGTH_LONG)
                                        .show()
                                    users.document(email).set(user)
                                    val intent = Intent(this, MainActivity::class.java)
                                    intent.putExtra("email", email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                    } else {
                        Toast.makeText(this, "User already Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

            } else {
                Toast.makeText(this, "Enter the Details", Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun checking(): Boolean {
        if (edtEmail.text.toString().trim { it <= ' ' }.isNotEmpty() && edtPassword.text.toString()
                .trim { it <= ' ' }.isNotEmpty() && edtName.text.toString().trim { it <= ' ' }
                .isNotEmpty() && edtContactNo.text.toString().trim { it <= ' ' }.isNotEmpty()
        ) {
            return true
        }
        return false
    }
}