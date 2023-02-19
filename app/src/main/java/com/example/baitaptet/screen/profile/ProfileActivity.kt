package com.example.baitaptet.screen.profile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.baitaptet.R

class ProfileActivity : AppCompatActivity() {
    lateinit var stringname: String
    lateinit var stringemail: String
    lateinit var stringphone: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val fullNameTextView = findViewById<TextView>(R.id.textViewFullName)
        val emailTextView = findViewById<TextView>(R.id.textViewEmail)
        val phoneTextView = findViewById<TextView>(R.id.textViewPhone)
        data class User(
            val fullName: String,
            val email: String,
            val phoneNumber: String
        )
        val user = User("John Doe", "john.doe@example.com", "0123456789")
        fullNameTextView.text = user.fullName
        emailTextView.text = user.email
        phoneTextView.text = user.phoneNumber
        val sharedPreferences = getSharedPreferences("mySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("fullName", fullNameTextView.text.toString())
        editor.putString("email", emailTextView.text.toString())
        editor.putString("phoneNumber", phoneTextView.text.toString())

        editor.apply()

    }

}