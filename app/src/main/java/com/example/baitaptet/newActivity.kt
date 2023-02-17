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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.signup.setOnClickListener{
            Signup()
        }
        binding.Login.setOnClickListener{
            Login()
        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun Signup(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
    private fun Login() {

        val username = binding.username.text.toString().trim()
        val password = binding.password.text.toString().trim()


        val data = intent.extras
        if (data != null) {
            val signupUsername = data.getString("username")
            val signupPassword = data.getString("password")
            if (!signupUsername.isNullOrEmpty() && !signupPassword.isNullOrEmpty()) {

                viewModel.login(signupUsername, signupPassword)
                return
            }
        }


        viewModel.login(username, password)
        val intent = Intent(this, ProfileActivity::class.java)
        val bundle = Bundle()
        bundle.putString("username", username)
        intent.putExtras(bundle)
        startActivity(intent)
    }
    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                val userName = binding.username.text.toString().trim()
                val password = binding.password.text.toString().trim()
                val intent = Intent(this, ProfileActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable(Activity.KEY_USER, UserNameProfile(userName, password))
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

}


