package com.example.baitaptet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val button6 = findViewById<Button>(R.id.button6)
        button.setOnClickListener {
            val intent: Intent = Intent (this, MainActivity1::class.java)
            startActivity(intent)


        }
    }
}