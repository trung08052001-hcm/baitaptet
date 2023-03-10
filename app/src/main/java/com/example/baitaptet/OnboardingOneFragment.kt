package com.example.baitaptet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.baitaptet.databinding.FragmentOnboardingOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OnboardingOneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnboardingOneFragment : Fragment() {
    lateinit var binding : FragmentOnboardingOneBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentOnboardingOneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        val onboardingShown = sharedPreferences.getBoolean("onboarding_shown", false)
        if (onboardingShown) {
            findNavController().navigate(R.id.welcomeFragment)
        }

        binding.imageNext1.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.onboardingTwoFragment2)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        // Cập nhật trạng thái đã hiển thị onboarding
        val sharedPreferences = requireActivity().getSharedPreferences("onboarding", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
    }
}