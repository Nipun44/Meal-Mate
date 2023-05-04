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

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var foodList: ArrayList<FoodModel>
    private lateinit var dbRef: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fetching_food)

        empRecyclerView = findViewById(R.id.rvFood)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        foodList = arrayListOf<FoodModel>()

        getFoodsData()
    }
    private fun getFoodsData() {
        empRecyclerView.visibility = View.GONE
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
                    empRecyclerView.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object: FoodAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@FetchingFood,SingleItemView::class.java)

                            intent.putExtra("foodId",foodList[position].foodId)
                            intent.putExtra("foodName",foodList[position].typeFood)
                            intent.putExtra("foodDesc",foodList[position].descriptionFood)
                            intent.putExtra("foodQuantity",foodList[position].quantityFood)

                            startActivity(intent)
                        }

                    })

                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}