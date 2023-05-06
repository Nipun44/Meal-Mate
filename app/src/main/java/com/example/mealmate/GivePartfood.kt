package com.example.mealmate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.mealmate.models.FoodModel
//import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class GivePartfood : AppCompatActivity() {

    private lateinit var selectType: EditText
    private lateinit var quantity: EditText
    private lateinit var description: EditText
    //    private lateinit var anonymous: RadioButton
    private lateinit var btnConfirm: Button
    private lateinit var btnBack: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_partfood)

        selectType = findViewById(R.id.typeField)
        quantity = findViewById(R.id.quantityfeeld)
        description = findViewById(R.id.descriptinfeld)

        btnConfirm = findViewById(R.id.button5)
        btnBack = findViewById(R.id.button6)

        dbRef = FirebaseDatabase.getInstance().getReference("Foods")

        btnConfirm.setOnClickListener {
            saveFoodData()
        }





    }

    private fun saveFoodData(){

//        println("other message")
//        println(FirebaseAuth.getInstance().currentUser?.uid)
        //geting values

        val typeFood = selectType.text.toString()
        val quantityFood = quantity.text.toString()
        val descriptionFood = description.text.toString()
//        val anonymousFood = anonymous.text.toString()





        if(typeFood.isEmpty()){
            selectType.error = "please enter type"
        }

        if(quantityFood.isEmpty()){
            quantity.error = "please enter quantity"
        }

        if(descriptionFood.isEmpty()){
            description.error = "please enter description"
        }


        val sharedPref = applicationContext.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val email = sharedPref.getString("Email", "").toString()
//        val uid = FirebaseAuth.getInstance().currentUser?.uid

        val foodId = dbRef.push().key!!

//        val foods = FoodModel(foodId, typeFood, quantityFood, descriptionFood,uid)
        val foods = FoodModel(foodId, typeFood, quantityFood, descriptionFood,email)

        dbRef.child(foodId).setValue(foods)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()


                quantity.text.clear()
                description.text.clear()
                selectType.text.clear()



            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

            }
    }
}