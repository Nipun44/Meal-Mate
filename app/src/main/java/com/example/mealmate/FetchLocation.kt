package com.example.mealmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.adapters.LocationAdapter
import com.example.mealmate.models.LocationModel
import com.google.firebase.database.*
import java.awt.font.TextAttribute
import java.util.Objects

class FetchLocation : AppCompatActivity() {

    private lateinit var locRecyclerView : RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var locList : ArrayList<LocationModel>
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_location)

        locRecyclerView = findViewById(R.id.rvLocation)
        locRecyclerView.layoutManager  = LinearLayoutManager(this)
        locRecyclerView.setHasFixedSize(true)
        tvLoadingData =   findViewById(R.id.tvLoadingData)
        locList = arrayListOf<LocationModel>()

        getLocationData()
    }
    private fun getLocationData(){
        locRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Locations")


        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                locList.clear()
                if(snapshot.exists()){
                    for (locSnap in snapshot.children){
                        val locData = locSnap.getValue(LocationModel::class.java)
                        locList.add(locData!!)
                    }
                    val lAdapter = LocationAdapter(locList)
                    locRecyclerView.adapter = lAdapter

                    locRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}


