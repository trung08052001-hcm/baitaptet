package com.example.baitaptet.screen.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.viewmodel.ProfileViewModel
import com.example.baitaptet.R
import com.example.baitaptet.RestaurantsActivity
import com.example.baitaptet.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.initSharedPreferences(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.contunue.setOnClickListener{
            val intent = Intent(this, RestaurantsActivity::class.java)
            startActivity(intent)
        }
}}