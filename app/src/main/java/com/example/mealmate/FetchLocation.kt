package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
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
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_location)

        locRecyclerView = findViewById(R.id.rvLocation)
        locRecyclerView.layoutManager  = LinearLayoutManager(this)
        locRecyclerView.setHasFixedSize(true)
        tvLoadingData =   findViewById(R.id.tvLoadingData)
        locList = arrayListOf<LocationModel>()

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filterLocations(newText)
                }
                return true
            }
        })

        getLocationData()
    }

    private fun filterLocations(query: String) {
        val filteredList = ArrayList<LocationModel>()
        for (location in locList) {
            if (location.locationName!!.contains(query, ignoreCase = true)) {
                filteredList.add(location)
            }
        }
        val lAdapter = LocationAdapter(filteredList)
        locRecyclerView.adapter = lAdapter

        lAdapter.setOnItemClickListener(object : LocationAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                // ...
            }
        })
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

                    lAdapter.setOnItemClickListener(object : LocationAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            var intent = Intent(this@FetchLocation,LocationDetails::class.java)

                            intent.putExtra("Location id",locList[position].locationId)
                            intent.putExtra("Location Name",locList[position].locationName)
                            intent.putExtra("Address",locList[position].LocatoinAddress)
                            intent.putExtra("Description",locList[position].locationDescription)

                            startActivity(intent)

                        }

                    })

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


