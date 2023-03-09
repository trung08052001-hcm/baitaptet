package com.example.baitaptet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.baitaptet.databinding.FragmentProfileBinding
import com.example.baitaptet.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding
    lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.initSharedPreferences(requireContext())
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.contunue.setOnClickListener {

        }
    }
}