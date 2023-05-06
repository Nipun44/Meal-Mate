package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mealmate.models.LocationModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LocationDetails : AppCompatActivity() {

    private lateinit var tvLocId : TextView
    private lateinit var tvLocName : TextView
    private lateinit var tvLocAddress : TextView
    private lateinit var tvLocDescription : TextView
    private lateinit var btnUpdate : Button
    private lateinit var btnDelete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateaDialog(

                intent.getStringExtra("Location id").toString(),
                intent.getStringExtra("Location Name").toString(),
            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("Location id").toString()
            )
        }

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Locations").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Location Removed",Toast.LENGTH_LONG).show()
            val intent = Intent(this,FetchLocation::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->
            Toast.makeText(this,"error ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView(){

        tvLocId = findViewById(R.id.tvLocId)
        tvLocName = findViewById(R.id.tvLocName)
        tvLocAddress = findViewById(R.id.tvLocAddress)
        tvLocDescription = findViewById(R.id.tvLocDescription)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)

    }

    private fun setValuesToViews(){

        tvLocId.text = intent.getStringExtra("Location id")
        tvLocName.text = intent.getStringExtra("Location Name")
        tvLocAddress.text = intent.getStringExtra("Address")
        tvLocDescription.text = intent.getStringExtra("Description")

    }

    private fun openUpdateaDialog(
        locId : String,
        locName : String,

    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.location_update_dialog,null)

        mDialog.setView(mDialogView)

        val etLocName = mDialogView.findViewById<EditText>(R.id.etlocName)
        val etLocAddress = mDialogView.findViewById<EditText>(R.id.etlocAddress)
        val etLocDescription = mDialogView.findViewById<EditText>(R.id.etlocDescription)
        val btnLocUpdateData = mDialogView.findViewById<Button>(R.id.btnLocUpdateData)

        etLocName.setText(intent.getStringExtra("Location Name").toString())
        etLocAddress.setText(intent.getStringExtra("Address").toString())
        etLocDescription.setText(intent.getStringExtra("Description").toString())

        mDialog.setTitle("Updating $locName")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnLocUpdateData.setOnClickListener {
            updateLocationData(
                locId,
                etLocName.text.toString(),
                etLocAddress.text.toString(),
                etLocDescription.text.toString()
            )
            Toast.makeText(applicationContext,"Location Data Updated",Toast.LENGTH_LONG).show()

            tvLocName.text = etLocName.text.toString()
            tvLocAddress.text = etLocAddress.text.toString()
            tvLocDescription.text = etLocDescription.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateLocationData(
        id : String,
        name : String,
        address : String,
        description : String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Locations").child(id)
        val locInfo = LocationModel(id,name,address,description)
        dbRef.setValue(locInfo)
    }
}
