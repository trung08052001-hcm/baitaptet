package com.example.baitaptet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            val intent: Intent = Intent (this, MainActivity2::class.java)
            startActivity(intent)

        }
    }
}