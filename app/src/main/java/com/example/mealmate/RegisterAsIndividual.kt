package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
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

//
//package com.example.mealmate
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//
//
//class RegisterAsIndividual : AppCompatActivity() {
//    private lateinit var auth: FirebaseAuth
//    private lateinit var haveAccount: TextView
//    private lateinit var edtName: EditText
//    private lateinit var edtEmail: EditText
//    private lateinit var edtContactNo: EditText
//    private lateinit var edtPassword: EditText
//    private lateinit var btnSignup: Button
//
//    //    private lateinit var db: FirebaseFirestore
//    private lateinit var dbRef: DatabaseReference
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register2)
//
////        db = FirebaseFirestore.getInstance()
//        dbRef = FirebaseDatabase.getInstance().getReference("USERS")
//
//        haveAccount = findViewById(R.id.already_have_account)
//        edtName = findViewById(R.id.edtPersonName)
//        edtEmail = findViewById(R.id.edtEmail)
//        edtContactNo = findViewById(R.id.edtContactNo)
//        edtPassword = findViewById(R.id.edtPassword)
//        btnSignup = findViewById(R.id.btnSignUpIndReg)
//        auth = FirebaseAuth.getInstance()
//
//        haveAccount.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        btnSignup.setOnClickListener {
//            if (checking()) {
//                val email = edtEmail.text.toString()
//                val password = edtPassword.text.toString()
//                val name = edtName.text.toString()
//                val contact = edtContactNo.text.toString()
//                val user = hashMapOf(
//                    "name" to name,
//                    "contact" to contact,
//                    "password" to password,
//                    "email" to email
//                )
//                val users = dbRef.child("USERS")
//                val query = users.orderByChild("email").equalTo(email)
//                    .addListenerForSingleValueEvent(object : ValueEventListener {
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//                            if (dataSnapshot.exists()) {
//                                Toast.makeText(
//                                    this@RegisterAsIndividual, "User already registered",
//                                    Toast.LENGTH_LONG
//                                ).show()
//                                val intent =
//                                    Intent(this@RegisterAsIndividual, MainActivity::class.java)
//                                startActivity(intent)
//                            } else {
//                                FirebaseAuth.getInstance()
//                                    .createUserWithEmailAndPassword(email, password)
//                                    .addOnCompleteListener(this@RegisterAsIndividual) { task ->
//                                        if (task.isSuccessful) {
//                                            Toast.makeText(
//                                                this@RegisterAsIndividual, "Sign up success",
//                                                Toast.LENGTH_LONG
//                                            ).show()
//                                            users.child(email.replace(".", ",")).setValue(user)
//                                            val intent = Intent(
//                                                this@RegisterAsIndividual,
//                                                MainActivity::class.java
//                                            )
//                                            intent.putExtra("email", email)
//                                            startActivity(intent)
//                                            finish()
//                                        } else {
//                                            Toast.makeText(
//                                                this@RegisterAsIndividual, "Authentication failed",
//                                                Toast.LENGTH_LONG
//                                            ).show()
//                                        }
//                                    }
//                            }
//                        }
//
//                        override fun onCancelled(databaseError: DatabaseError) {
//                            Log.d("RegisterAsIndividual", databaseError.message)
//                        }
//                    })
//            }
//
//        }
//
//    }
//        private fun checking(): Boolean {
//            if (edtEmail.text.toString().trim { it <= ' ' }
//                    .isNotEmpty() && edtPassword.text.toString()
//                    .trim { it <= ' ' }.isNotEmpty() && edtName.text.toString().trim { it <= ' ' }
//                    .isNotEmpty() && edtContactNo.text.toString().trim { it <= ' ' }.isNotEmpty()
//            ) {
//                return true
//            }
//            return false
//        }
//}