package com.example.baitaptet

import android.content.Context
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
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val onboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)
        if (onboardingShown) {
            findNavController().navigate(R.id.welcomeFragment)
        }
        binding.imageNext3.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.welcomeFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        // Cập nhật trạng thái đã hiển thị onboarding
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
    }
}