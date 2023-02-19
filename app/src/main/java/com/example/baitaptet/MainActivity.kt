package com.example.baitaptet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import com.example.baitaptet.screen.wellcome.MainActivity1
import com.example.baitaptet.screen.wellcome.MainActivity2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity1::class.java)
            startActivity(intent)
        }, 5000)
    }
}