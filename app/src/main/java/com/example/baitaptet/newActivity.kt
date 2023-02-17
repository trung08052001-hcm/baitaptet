package com.example.baitaptet

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.databinding.ActivityNewBinding


class newActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.signup.setOnClickListener{
            val intent: Intent = Intent (this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.Login.setOnClickListener {
            login()
        }

        viewModel.isLoginSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

                finish()
            }
        }

        viewModel.error.observe(this) { error ->
            when (error) {
                Error.ERROR_ACCOUNT -> Toast.makeText(
                    this,
                    "Account is already logged in",
                    Toast.LENGTH_SHORT
                ).show()
                Error.ERROR_LOGIN_FAILED -> Toast.makeText(
                    this,
                    "Incorrect email or password",
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    // Handle other error cases
                }
            }
        }
    }

    private fun login() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()
        viewModel.login(username, password)
        viewModel.isLoginSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
        }
    }
}


