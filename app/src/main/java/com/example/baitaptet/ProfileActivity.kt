package com.example.baitaptet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ProfileActivity : AppCompatActivity() {
    lateinit var stringname: String
    lateinit var stringemail: String
    lateinit var stringphone: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val btnsave = findViewById<Button>(R.id.Save)
        val name = findViewById<EditText>(R.id.Fullname)
        val email= findViewById<EditText>(R.id.Email)
        val phone = findViewById<EditText>(R.id.PhoneNumber)
        val txtname = findViewById<TextView>(R.id.textfullname)
        val txtemail = findViewById<TextView>(R.id.textemail)
        val txtphone = findViewById<TextView>(R.id.textphonenumber)
        btnsave.setOnClickListener{
            stringname = name.text.toString()
            txtname.text=stringname
            stringemail= email.text.toString()
            txtemail.text=stringemail
            stringphone= phone.text.toString()
            txtphone.text=stringphone
            Toast.makeText(this,"Save Successfully",Toast.LENGTH_LONG).show()
        }
    }


}