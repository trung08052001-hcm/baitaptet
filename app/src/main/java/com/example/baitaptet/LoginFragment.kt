package com.example.baitaptet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.baitaptet.databinding.FragmentLoginBinding
import com.example.baitaptet.databinding.FragmentOnboardingOneBinding
import com.example.baitaptet.viewmodel.MainViewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.initSharedPreferences(requireContext())

        binding.Signup.setOnClickListener{

        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailInputText.text.toString()
            val password = binding.passwordInputText.text.toString()
            viewModel.checkEmailAndPassword(requireContext(), email, password)
        }

        viewModel.isSuccessEvent.observe(viewLifecycleOwner, { success ->
            if (success) {
                requireActivity().finish()
            }
        })

        viewModel.isErrorEvent.observe(viewLifecycleOwner, { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        })
    }
}