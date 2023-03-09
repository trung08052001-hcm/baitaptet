package com.example.baitaptet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.baitaptet.databinding.FragmentOnboardingThreeBinding
import com.example.baitaptet.databinding.FragmentOnboardingTwoBinding


class OnboardingThreeFragment : Fragment() {
    lateinit var binding : FragmentOnboardingThreeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageNext3.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.welcomeFragment)
        }
    }
}