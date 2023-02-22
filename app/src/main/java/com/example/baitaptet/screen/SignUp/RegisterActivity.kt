package com.example.baitaptet.screen.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.ViewModel.MainViewModel
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

        // Set up onClickListener cho nút đăng ký
        binding.buttonSignUp.setOnClickListener {
            val fullname = binding.fullNameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            viewModel.SignUp(this, fullname,email, password)
        }

        // Theo dõi sự thay đổi trạng thái đăng ký
        viewModel.isSuccessEvent.observe(this, {
            if (it) {
                // Đăng ký thành công
                Toast.makeText(this, "Đăng ký thành công.", Toast.LENGTH_SHORT).show()

                // Chuyển sang màn hình đăng nhập
                val intent = Intent(this, newActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        viewModel.isErrorEvent.observe(this, {
            // Hiển thị thông báo lỗi đăng ký
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}