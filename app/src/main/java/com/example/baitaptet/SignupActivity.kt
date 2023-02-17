package com.example.baitaptet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.logintxt.setOnClickListener{
            val intent = Intent(this, newActivity::class.java)
            startActivity(intent)
        }

        binding.signup.setOnClickListener {
            signup()
        }

        listenerSuccessEvent()
        listenerErrorEvent()
    }

    private fun signup() {
        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()
        val confirmPassword = binding.confirmPassword.text.toString().trim()
        viewModel.signup(username, password ,confirmPassword)

    }

    private fun listenerSuccessEvent() {
        viewModel.isSignupSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val username = binding.username.text.toString().trim()
                val password = binding.password.text.toString().trim()
                val intent = Intent(this, newActivity::class.java)
                intent.putExtra("username", username)
                intent.putExtra("password", password)
                startActivity(intent)
            }
        }
    }

    private fun listenerErrorEvent() {
        viewModel.error.observe(this) { error ->
            when (error) {
                Error.ERROR_ACCOUNT -> Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                Error.ERROR_PASSWORD -> Toast.makeText(this, "Password must be between 8 and 10 characters", Toast.LENGTH_SHORT).show()
                Error.ERROR_CONFIRM_PASSWORD -> Toast.makeText(this, "Confirm password does not match", Toast.LENGTH_SHORT).show()
                else -> {
                    // Handle other error cases
                }
            }
        }
    }

    private fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }


}