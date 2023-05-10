package com.example.mealmate

import NoticeAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealmate.models.NoticeModel
import com.google.firebase.database.*

class listNotices : AppCompatActivity() {

    private lateinit var noticeRecycleView: RecyclerView
    private lateinit var tvNotice: TextView
    private lateinit var noticeList: ArrayList<NoticeModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var search: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_notices)

        noticeRecycleView = findViewById(R.id.rvNotice)
        noticeRecycleView.layoutManager = LinearLayoutManager(this)
        noticeRecycleView.setHasFixedSize(true)
        tvNotice = findViewById(R.id.tvLoadingNotice)
        search = findViewById(R.id.searchView)
        noticeList = arrayListOf<NoticeModel>()








        getNoticeData()


    }
    private fun getNoticeData(query: String = "") {
        noticeRecycleView.visibility = View.GONE
        tvNotice.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Notices")

        val searchQuery = if (query.isEmpty()) dbRef else dbRef.orderByChild("nPlace").startAt(query).endAt("$query\uf8ff")

        searchQuery.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                noticeList.clear()
                if(snapshot.exists()){
                    for (noticeSnap in snapshot.children){
                        val noticeData = noticeSnap.getValue(NoticeModel::class.java)
                        noticeList.add(noticeData!!)
                    }
                    val mAdaptor = NoticeAdapter(noticeList)
                    noticeRecycleView.adapter = mAdaptor

                    mAdaptor.setOnItemClickListener(object : NoticeAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@listNotices, noticeDetails::class.java)
                            intent.putExtra("nID",noticeList[position].noticeID)
                            intent.putExtra("nDate", noticeList[position].nDate)
                            intent.putExtra("nTopic", noticeList[position].nTopic)
                            intent.putExtra("nPlace", noticeList[position].nPlace)
                            intent.putExtra("nDescrtption", noticeList[position].nDescrtption)//need to add date

                            startActivity(intent)
                        }

                    })

                    noticeRecycleView.visibility = View.VISIBLE
                    tvNotice.visibility = View.GONE

                } else {
                    noticeRecycleView.adapter = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    getNoticeData(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    getNoticeData(newText)
                    return true
                } else {
                    getNoticeData()
                }
                return false
            }
        })
    }
}