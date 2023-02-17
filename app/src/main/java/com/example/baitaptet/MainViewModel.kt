package com.example.baitaptet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



enum class Error {
    ERROR_ACCOUNT,
    ERROR_PASSWORD,
    ERROR_CONFIRM_PASSWORD,
    ERROR_LOGIN_FAILED
}


class MainViewModel : ViewModel() {

    private val _isSignupSuccess = MutableLiveData<Boolean>()
    val isSignupSuccess: LiveData<Boolean>
        get() = _isSignupSuccess

    private val _isLoginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean>
        get() = _isLoginSuccess

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error>
        get() = _error

    private var loggedInAccount: String? = null

    fun login(username: String, password: String) {

        if (loggedInAccount != null) {
            _error.value = Error.ERROR_ACCOUNT
            return
        }

        // Kiểm tra tài khoản và mật khẩu
        if (username == "example@example.com" && password == "password") {
            loggedInAccount = username
            _isLoginSuccess.value = true
        } else {
            _error.value = Error.ERROR_LOGIN_FAILED
        }
    }

    fun signup(username: String, password: String, confirmPassword: String) {

        // Kiểm tra định dạng tài khoản
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            _error.value = Error.ERROR_ACCOUNT
            return
        }

        // Kiểm tra độ dài mật khẩu
        if (password.length !in 8..10) {
            _error.value = Error.ERROR_PASSWORD
            return
        }

        // Kiểm tra mật khẩu nhập lại có khớp với mật khẩu không
        if (password != confirmPassword) {
            _error.value = Error.ERROR_CONFIRM_PASSWORD
            return
        }

        // Lưu tài khoản mới và đăng nhập thành công
        loggedInAccount = username
        _isSignupSuccess.value = true
    }
}