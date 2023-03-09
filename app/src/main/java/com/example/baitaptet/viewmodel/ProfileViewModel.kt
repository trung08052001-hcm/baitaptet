package com.example.baitaptet.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private lateinit var sharedPreferences: SharedPreferences

    private val _isLoggedOut = MutableLiveData<Boolean>()
    val isLoggedOut: LiveData<Boolean>
        get() = _isLoggedOut

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
    }

    fun getFullName(): String {
        return sharedPreferences.getString("fullName", "") ?: ""
    }

    fun getEmail(): String {
        return sharedPreferences.getString("email", "") ?: ""
    }

    fun clearSharedPreferences() {
        sharedPreferences.edit().clear().apply()
        _isLoggedOut.value = true
    }
}


