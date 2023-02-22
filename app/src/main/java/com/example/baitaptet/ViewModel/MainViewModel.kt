package com.example.baitaptet.ViewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun checkEmailAndPassword(context: Context, email: String, password: String) {
        val savedEmail = sharedPreferences.getString("email", "")
        val savedPassword = sharedPreferences.getString("password", "")

        if (email == savedEmail && password == savedPassword) {
            _isSuccessEvent.postValue(true)
        } else {
            _isErrorEvent.postValue("Thông tin đăng nhập không hợp lệ.")
        }
    }

    fun SignUp(context: Context,fullName : String , email: String, password: String) {
        if (fullName.isEmpty()) {
            _isErrorEvent.postValue("Vui lòng nhập họ tên của bạn.")
            return
        }
        if (!isEmailValid(email)) {
            _isErrorEvent.postValue("Email không hợp lệ.")
            return
        }

        if (!isPasswordValid(password)) {
            _isErrorEvent.postValue("Mật khẩu không hợp lệ.")
            return
        }

        // Lưu thông tin đăng ký vào SharedPreferences
        editor.putString("fullName", fullName)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()

        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 8..10
    }
}