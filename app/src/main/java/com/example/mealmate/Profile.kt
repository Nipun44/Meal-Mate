package com.example.mealmate

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class Profile : AppCompatActivity() {
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var tvUserContact: TextView
    private lateinit var btnProfileUpdate: Button
    private lateinit var btnProfileDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvUserName = findViewById(R.id.tvUserName)
        tvUserEmail = findViewById(R.id.tvUserEmail)
        tvUserContact = findViewById(R.id.tvUserContact)
        btnProfileDelete = findViewById(R.id.btnDeleteProfile)
        btnProfileUpdate = findViewById(R.id.btnUpdateProfile)

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

        btnProfileUpdate.setOnClickListener {
            openUpdateDialog(name, contact, email)
        }
    }

    private fun openUpdateDialog(userName: String, userContact: String, email: String) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_user, null)

        mDialog.setView(mDialogView)

        val etUserName = mDialogView.findViewById<EditText>(R.id.etUserName)
        val etContactNo = mDialogView.findViewById<EditText>(R.id.etUserContactNumber)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etUserName.setText(userName)
        etContactNo.setText(userContact)

        mDialog.setTitle("$userName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateUserData(etUserName.text.toString(), etContactNo.text.toString(), email)

            Toast.makeText(applicationContext, "User Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvUserName.text = etUserName.text.toString()
            tvUserContact.text = etContactNo.text.toString()
            alertDialog.dismiss()
        }

    }

    private fun updateUserData(name: String, contact: String, email: String) {
        val dbRef = FirebaseFirestore.getInstance().collection("USERS")
        val user = hashMapOf(
            "name" to name,
            "contact" to contact
        )
        dbRef.document(email).update(user as Map<String, Any>)
    }
}
