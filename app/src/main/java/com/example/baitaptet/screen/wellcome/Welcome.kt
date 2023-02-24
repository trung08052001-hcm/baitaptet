package com.example.baitaptet.screen.wellcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.baitaptet.R
import com.example.baitaptet.screen.login.newActivity

class welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        var btn = findViewById<Button>(R.id.buttonStartWithEmailOrPhone)
        btn.setOnClickListener{
            val intent: Intent = Intent (this, newActivity::class.java)
            startActivity(intent)
        }
    }
}