package com.example.baitaptet

import android.content.Context
import android.content.SharedPreferences
import android.util.Patterns
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
            _isErrorEvent.postValue("Invalid login information.")
        }
    }

    fun SignUp(context: Context,fullName : String , email: String, password: String) {
        if (fullName.isEmpty()) {
            _isErrorEvent.postValue("Please enter your full name")
            return
        }
        if (!isEmailValid(email)) {
            _isErrorEvent.postValue("Email is invalid")
            return
        }

        if (!isPasswordValid(password)) {
            _isErrorEvent.postValue("Password is invalid")
            return
        }


        editor.putString("fullName", fullName)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()

        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS
        return emailPattern.matcher(email).matches() && email.endsWith("@gmail.com")
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$".toRegex()
        return passwordPattern.matches(password)
    }
}