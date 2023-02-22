package com.example.baitaptet.screen.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.MainViewModel
import com.example.baitaptet.R
import com.example.baitaptet.databinding.ActivityRegisterBinding
import com.example.baitaptet.screen.login.newActivity

class registerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.initSharedPreferences(this)


        binding.buttonSignUp.setOnClickListener {
            val fullname = binding.fullNameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            viewModel.SignUp(this, fullname,email, password)
        }


        viewModel.isSuccessEvent.observe(this, {
            if (it) {

                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()


                val intent = Intent(this, newActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        viewModel.isErrorEvent.observe(this, {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}