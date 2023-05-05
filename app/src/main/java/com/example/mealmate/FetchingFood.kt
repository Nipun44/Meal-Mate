//package com.example.mealmate
//
//import android.annotation.SuppressLint
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.TextView
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.mealmate.adapters.FoodAdapter
//import com.example.mealmate.models.FoodModel
//import com.google.firebase.database.*
//
//class FetchingFood : AppCompatActivity() {
//
//    private lateinit var foodRecyclerView: RecyclerView
//    private lateinit var tvLoadingData: TextView
//    private lateinit var foodList: ArrayList<FoodModel>
//    private lateinit var dbRef: DatabaseReference
//
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fetching_food)
//
//        foodRecyclerView = findViewById(R.id.rvFood)
//        foodRecyclerView.layoutManager = LinearLayoutManager(this)
//        foodRecyclerView.setHasFixedSize(true)
//        tvLoadingData = findViewById(R.id.tvLoadingData)
//
//        foodList = arrayListOf<FoodModel>()
//
//        getFoodsData()
//    }
//    private fun getFoodsData() {
//        foodRecyclerView.visibility = View.GONE
//        tvLoadingData.visibility = View.VISIBLE
//
//        dbRef = FirebaseDatabase.getInstance().getReference("Foods")
//
//        dbRef.addValueEventListener(object: ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                foodList.clear()
//                if(snapshot.exists()){
//                    for(empSnap in snapshot.children){
//                        val foodData = empSnap.getValue(FoodModel::class.java)
//                        foodList.add(foodData!!)
//                    }
//                    val mAdapter = FoodAdapter(foodList)
//                    foodRecyclerView.adapter = mAdapter
//
//                    mAdapter.setOnItemClickListener(object : FoodAdapter.onItemClickListener{
//                        override fun onItemClick(position: Int) {
//                            val intent = Intent(this@FetchingFood,FoodDetails::class.java)
//
//                            //put the  extra values in foodList
//
//                            intent.putExtra("foodId",foodList[position].foodId)
//                            intent.putExtra("foodType",foodList[position].typeFood)
//                            intent.putExtra("foodQuantity",foodList[position].quantityFood)
//                            intent.putExtra("foodDescription",foodList[position].descriptionFood)
//                            startActivity(intent)
//
//
//
//
//
//                        }
//
//                    })
//
//                    foodRecyclerView.visibility = View.VISIBLE
//                    tvLoadingData.visibility = View.GONE
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
//
//
//}




package com.example.mealmate

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.adapters.FoodAdapter
import com.example.mealmate.models.FoodModel
import com.google.firebase.database.*

class FetchingFood : AppCompatActivity() {

    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var foodList: ArrayList<FoodModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching_food)

        foodRecyclerView = findViewById(R.id.rvFood)
        foodRecyclerView.layoutManager = LinearLayoutManager(this)
        foodRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        foodList = arrayListOf<FoodModel>()

        getFoodsData()
    }
    private fun getFoodsData() {
        foodRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Foods")

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                foodList.clear()
                if(snapshot.exists()){
                    for(empSnap in snapshot.children){
                        val foodData = empSnap.getValue(FoodModel::class.java)
                        foodList.add(foodData!!)
                    }
                    val mAdapter = FoodAdapter(foodList)
                    foodRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : FoodAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingFood,FoodDetails::class.java)

                            //put the  extra values in foodList

                            intent.putExtra("foodId",foodList[position].foodId)
                            intent.putExtra("foodType",foodList[position].typeFood)
                            intent.putExtra("foodQuantity",foodList[position].quantityFood)
                            intent.putExtra("foodDescription",foodList[position].descriptionFood)
                            startActivity(intent)





                        }

                    })

                    foodRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}