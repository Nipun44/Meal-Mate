package com.example.mealmate

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mealmate.models.FoodModel
import com.google.firebase.database.FirebaseDatabase

class FoodDetails : AppCompatActivity() {


    private lateinit var tvFoodId: TextView
    private lateinit var tvFoodType: TextView
    private lateinit var tvQuantity: TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("foodId").toString(),

                intent.getStringExtra("foodType").toString()

            )
        }

        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("foodId").toString()
            )
        }
    }

    private fun deleteRecord(
        id: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Foods").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Food Data Deleted ", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingFood::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Err ${error.message} ", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvFoodId = findViewById(R.id.tvFoodId)
        tvFoodType = findViewById(R.id.tvFoodType)
        tvQuantity = findViewById(R.id.tvFoodQuantity)
        tvDescription = findViewById(R.id.tvFoodDescription)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }


    //set values for a view in
    private fun setValuesToViews() {
        tvFoodId.text = intent.getStringExtra("foodId")
        tvFoodType.text = intent.getStringExtra("foodType")
        tvQuantity.text = intent.getStringExtra("foodQuantity")
        tvDescription.text = intent.getStringExtra("foodDescription")
    }


    private fun openUpdateDialog(
        foodId: String,
        foodType: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etFoodType =  mDialogView.findViewById<EditText>(R.id.etFoodType)
        val etQuantity = mDialogView.findViewById<EditText>(R.id.etQuantity)
        val etDescription = mDialogView.findViewById<EditText>(R.id.etDescription)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etFoodType.setText(intent.getStringExtra("foodType").toString())
        etQuantity.setText(intent.getStringExtra("foodQuantity").toString())
        etDescription.setText(intent.getStringExtra("foodDescription").toString())


        //methanata foodtype eke type damai
        mDialog.setTitle("Updating $etFoodType Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateFoodData(
                foodId,
                etFoodType.text.toString(),
                etQuantity.text.toString(),
                etDescription.text.toString()
            )
            Toast.makeText(applicationContext, "Food Data updated", Toast.LENGTH_LONG).show()


            //we are setting update data to our text views
            tvFoodType.text = etFoodType.text.toString()
            tvQuantity.text = etQuantity.text.toString()
            tvDescription.text = etDescription.text.toString()


            alertDialog.dismiss()
        }

    }

    private fun updateFoodData(
        id: String,
        foodType:String,
        quantity: String,
        description: String,

    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Foods").child(id)
        val foodInfo = FoodModel(id,foodType, quantity, description)
        dbRef.setValue(foodInfo)
    }
}