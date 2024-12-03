package com.ecoliving.mobile.presentation.ui.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.ecoliving.mobile.presentation.ui.dashboard.DashboardViewModel
import com.ecoliving.mobile.presentation.ui.login.LoginViewModel
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentOnboardBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class OnboardFragment : Fragment() {
    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOnboardingUI()

    }

    private fun navigateToDashboard() {
        findNavController().navigate(R.id.action_onboardFragment_to_dashboardFragment)
    }

    private fun setupOnboardingUI() {
        with(binding.viewPager) {
            adapter = ViewPagerAdapter()
            binding.wormDotsIndicator.attachTo(this)
        }

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_loginFragment)
        }

        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_onboardFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}