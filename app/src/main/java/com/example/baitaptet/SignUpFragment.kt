package com.example.baitaptet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.baitaptet.databinding.FragmentSignUpBinding
import com.example.baitaptet.viewmodel.MainViewModel


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.initSharedPreferences(requireContext())

        binding.buttonSignUp.setOnClickListener {
            val fullname = binding.fullNameInput.text.toString().trim()
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            viewModel.SignUp(requireContext(), fullname, email, password)
        }

        viewModel.isSuccessEvent.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()

                val controller = findNavController()
                controller.navigate(R.id.loginFragment)
            }
        })

        viewModel.isErrorEvent.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}