package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.mealmate.models.FoodModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class GivePartFood : AppCompatActivity() {

    private lateinit var selectType: Spinner
    private lateinit var quantity: EditText
    private lateinit var description: EditText
    private lateinit var btnConfirm: Button
    private lateinit var btnBack: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_give_partfood)

        selectType = findViewById(R.id.spinner)
        quantity = findViewById(R.id.quantityfeeld)
        description = findViewById(R.id.descriptinfeld)
//        anonymous = findViewById(R.id.radio_pirates)
        btnConfirm = findViewById(R.id.button5)
        btnBack = findViewById(R.id.button6)

        dbRef = FirebaseDatabase.getInstance().getReference("Foods")

        btnConfirm.setOnClickListener {
            saveFoodData()
        }

    }

    private fun saveFoodData(){
        //geting values
        val typeFood = selectType.selectedItem.toString()
        val quantityFood = quantity.text.toString()
        val descriptionFood = description.text.toString()


        if(typeFood.isEmpty()){
            selectType.prompt = "please enter type"
        }

        if(quantityFood.isEmpty()){
            quantity.error = "please enter quantity"
        }

        if(descriptionFood.isEmpty()){
            description.error = "please enter description"
        }

        val foodId = dbRef.push().key!!

        val foods = FoodModel(foodId, typeFood, quantityFood, descriptionFood)

        dbRef.child(foodId).setValue(foods)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()


                quantity.text.clear()
                description.text.clear()



            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

            }
    }
}