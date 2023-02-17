package com.example.baitaptet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



enum class Error {
    ERROR_ACCOUNT,
    ERROR_PASSWORD,
}

class Resp(val isSuccess: Boolean, val error: Error?)

class MainViewModel : ViewModel() {
    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    fun login(username: String, password: String) {

        val isValidAccount = accountvalid(username)
        if (!isValidAccount) {
            _isErrorEvent.postValue("This account is Invalid")
            return
        }
        //password length > 8 && < 10
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("This password is Invalid")
            return
        }
        // check login with server
        // ...

        _isSuccessEvent.postValue(true)
    }

    fun signup(username: String, password: String, confirmPassword: String) {
        // Kiểm tra định dạng tài khoản
        val isValidAccount = accountvalid(username)
        if (!isValidAccount) {
            _isErrorEvent.postValue("Tài khoản không hợp lệ")
            return
        }

        // Kiểm tra độ dài mật khẩu
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("Mật khẩu không hợp lệ")
            return
        }

        // Kiểm tra mật khẩu nhập lại có khớp với mật khẩu không
        if (password != confirmPassword) {
            _isErrorEvent.postValue("Mật khẩu nhập lại không khớp với mật khẩu")
            return
        }

        // Thực hiện đăng ký tài khoản
        // ...

        // Nếu đăng ký thành công, đưa ra thông báo và chuyển sang màn hình đăng nhập
        _isSuccessEvent.postValue(true)
        _isErrorEvent.postValue("Đăng ký tài khoản thành công")
    }

    private fun accountvalid(account: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(account).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 8..10
    }
}