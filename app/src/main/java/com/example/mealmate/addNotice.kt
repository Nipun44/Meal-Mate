package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mealmate.models.NoticeModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addNotice : AppCompatActivity() {

    private lateinit var notice_topic: EditText
    private lateinit var notice_des: EditText
    private lateinit var notice_place: EditText
    private lateinit var notice_date: EditText
    private lateinit var notice_submit: Button
    private lateinit var notice_back: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notice)

        notice_topic = findViewById(R.id.notice_topic)
        notice_des = findViewById(R.id.notice_des)
        notice_place = findViewById(R.id.notice_place)
        notice_date = findViewById(R.id.notice_date)
        notice_submit = findViewById(R.id.notice_submit)
        notice_back = findViewById(R.id.notice_back)

        dbRef = FirebaseDatabase.getInstance().getReference("Notices")

        notice_submit.setOnClickListener {
            saveNotice()
        }

        notice_back.setOnClickListener {
            val intent = Intent(this, noticeDash::class.java)
            startActivity(intent)
        }



    }
    private fun saveNotice() {

        val nTopic = notice_topic.text.toString()
        val nDescritption = notice_des.text.toString()
        val nPlace = notice_place.text.toString()
        val nDate = notice_date.text.toString()

        if(nTopic.isEmpty()){
            notice_topic.error = "Plese enter the Topic"
        }
        if(nDescritption.isEmpty()){
            notice_des.error = "Please enter a Description"
        }
        if(nPlace.isEmpty()){
            notice_place.error = "Please enter a Place"
        }
        if(nDate.isEmpty()){
            notice_date.error = "Please enter a Date"
        }

        val noticeID = dbRef.push().key!!


        val notice = NoticeModel(noticeID, nTopic, nDescritption, nPlace, nDate)

        dbRef.child(noticeID).setValue(notice)
            .addOnCompleteListener {
                Toast.makeText(this, "Add Successfully", Toast.LENGTH_LONG).show()


                notice_topic.text.clear()
                notice_des.text.clear()
                notice_place.text.clear()
                notice_date.text.clear()



            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()

            }

    }
}