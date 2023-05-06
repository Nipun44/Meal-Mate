package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mealmate.models.LocationModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
/**/
class AddLocation : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference

    private lateinit var edtLocationName : EditText
    private lateinit var edtLocationAddress : EditText
    private lateinit var edtLocationDescription : EditText
    private lateinit var btnSaveLocation : Button

    private fun saveLocationData(){

        //getting values

        val locationName = edtLocationName.text.toString()
        val locationAddress = edtLocationAddress.text.toString()
        val locationDescription = edtLocationDescription.text.toString()

        //validation

        if (locationName.isEmpty()){
            edtLocationName.error = "Please enter a location name"
        }
        if (locationAddress.isEmpty()){
            edtLocationAddress.error = "Please enter a location address"
        }
        if (locationDescription.isEmpty()){
            edtLocationDescription.error = "Please enter a description"
        }

        val locationId = dbRef.push().key!!

        val location = LocationModel(locationId,locationName,locationAddress,locationDescription)

        dbRef.child(locationId).setValue(location).addOnCompleteListener {
            Toast.makeText(this, "Location Added", Toast.LENGTH_LONG).show()

            edtLocationName.text.clear()
            edtLocationAddress.text.clear()
            edtLocationDescription.text.clear()

        }.addOnFailureListener {err ->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        dbRef = FirebaseDatabase.getInstance().getReference("Locations")

        edtLocationName = findViewById(R.id.edtLocationName)
        edtLocationAddress = findViewById(R.id.edtLocationAddress)
        edtLocationDescription = findViewById(R.id.edtLocationDescription)
        btnSaveLocation = findViewById(R.id.btnSave)

        btnSaveLocation.setOnClickListener {
            saveLocationData()
        }


    }
}
