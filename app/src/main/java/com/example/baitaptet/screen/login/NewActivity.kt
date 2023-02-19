package com.example.baitaptet.screen.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.baitaptet.MainActivity
import com.example.baitaptet.R
import com.example.baitaptet.screen.profile.ProfileActivity

class newActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        var Login = findViewById<Button>(R.id.buttonLogin)
        Login.setOnClickListener{
            Login()
        }
    }
    private fun Login(){
        var username = findViewById<EditText>(R.id.emailInputText)
        var password = findViewById<EditText>(R.id.passwordInputText)
        if(username.text.toString().equals(Util.ACCOUNT) && password.text.toString().equals(Util.PASS_WORD)) {
            val builder = android.app.AlertDialog.Builder(this)

            builder.setMessage("Login success")
                .setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int -> Continue() })
                .show()
        } else {
            val builder = android.app.AlertDialog.Builder(this)

            builder.setMessage("Login faild")
                .setNegativeButton("Ok", { dialogInterface: DialogInterface, i: Int -> goHome()})
                .show()
        }
    }
    private fun goHome() {
        startActivity(Intent(this, newActivity::class.java))
    }
    private fun Continue() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    object Util {
        const val ACCOUNT = "username@gmail.com"
        const val PASS_WORD = "123456"
    }
}


