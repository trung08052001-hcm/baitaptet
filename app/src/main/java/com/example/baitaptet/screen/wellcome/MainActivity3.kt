package com.example.baitaptet.screen.wellcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.baitaptet.R
import com.example.baitaptet.screen.login.newActivity
import com.example.baitaptet.screen.profile.ProfileActivity

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val img = findViewById<ImageView>(R.id.imageNext3)
        img.setOnClickListener {
            val intent: Intent = Intent (this, newActivity::class.java)
            startActivity(intent)

        }
    }
}