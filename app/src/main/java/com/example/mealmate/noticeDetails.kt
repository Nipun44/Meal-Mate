package com.example.mealmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mealmate.models.NoticeModel
import com.google.firebase.database.FirebaseDatabase

class noticeDetails : AppCompatActivity() {
    private lateinit var tvNoticeId: TextView
    private lateinit var tvNTopic: TextView
    private lateinit var tvNPlace: TextView
    private lateinit var tvNDes: TextView
    private lateinit var tvNDate: TextView


    private lateinit var btnUpdate1: Button

    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_details)

        initView()
        setValuesToViews()

        btnUpdate1.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("nID").toString(),
                intent.getStringExtra("nTopic").toString()
            )
        }

        btnDelete.setOnClickListener{
            deleteRecord(
                intent.getStringExtra("nID").toString()
            )
        }
    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Notices").child(id)
        val nTask = dbRef.removeValue()

        nTask.addOnSuccessListener {
            Toast.makeText(this,"Notice is Successfully Deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, listNotices::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting error is occurred ", Toast.LENGTH_LONG).show()

        }
    }
    private fun initView(){
//        tvNoticeId = findViewById(R.id.tvNoticeId)
        tvNTopic = findViewById(R.id.tvNoticeTopic)
        tvNDes = findViewById(R.id.tvNoticeDescription)
        tvNPlace = findViewById(R.id.tvNoticePlace)
        tvNDate = findViewById(R.id.tvNoticeDate)

        btnUpdate1 = findViewById(R.id.btnUpdate1)
        btnDelete = findViewById(R.id.btnDelete)
    }


    private fun setValuesToViews(){

        tvNTopic.text = intent.getStringExtra("nTopic")
        tvNDes.text = intent.getStringExtra("nDescrtption")
        tvNPlace.text = intent.getStringExtra("nPlace")
        tvNDate.text = intent.getStringExtra("nDate")

    }

    private fun openUpdateDialog(
        noticeID: String,
        nTopic: String
    ){

        val nDialog = AlertDialog.Builder(this)
        val ninflater = layoutInflater
        val nDialogView = ninflater.inflate(R.layout.activity_notice_update_dialog,null)


        nDialog.setView(nDialogView)

        val etNoticeTopic = nDialogView.findViewById<EditText>(R.id.etNoticeTopic)
        val etNoticeDescription = nDialogView.findViewById<EditText>(R.id.etNoticeDescription)
        val etNoticePlace = nDialogView.findViewById<EditText>(R.id.etNoticePlace)
        //date
        val etNoticeDate = nDialogView.findViewById<EditText>(R.id.etNoticeDate)
        val btnUpdateData = nDialogView.findViewById<Button>(R.id.btnLocUpdateData)

        etNoticeTopic.setText(intent.getStringExtra("nTopic").toString())
        etNoticeDescription.setText(intent.getStringExtra("nDescrtption").toString())
        etNoticePlace.setText(intent.getStringExtra("nPlace").toString())
        etNoticeDate.setText(intent.getStringExtra("nDate").toString())


        nDialog.setTitle("Updating $nTopic ")

        val alertDialog = nDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateNoticeData(
                noticeID,
                etNoticeTopic.text.toString(),
                etNoticeDescription.text.toString(),
                etNoticePlace.text.toString(),
                etNoticeDate.text.toString()
            )

            Toast.makeText(applicationContext,"Data is updated", Toast.LENGTH_LONG).show()
//setting new updated data to textviews
            tvNTopic.text = etNoticeTopic.text.toString()
            tvNDes.text = etNoticeDescription.text.toString()
            tvNPlace.text = etNoticePlace.text.toString()
            tvNDate.text = etNoticeDate.text.toString()


            alertDialog.dismiss()
        }

    }

    private fun updateNoticeData(
        noticeID: String,
        nTopic: String,
        nDescrtption: String,
        nPlace: String,
        nDate: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Notices").child(noticeID)
        val noticeInfo = NoticeModel(noticeID, nTopic, nDescrtption, nPlace, nDate)
        dbRef.setValue(noticeInfo)
    }
}