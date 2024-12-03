package com.ecoliving.mobile.presentation.ui.splashscreen

import android.content.ContentValues.TAG
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.ecoliving.mobile.presentation.ui.dashboard.DashboardViewModel
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentLoginBinding
import com.example.ecoliving.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            Handler().postDelayed({
                if (isAdded) {
                    if (!user.isLogin) {
                        findNavController().navigate(R.id.action_splashFragment_to_onboardFragment)
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
                    }
                }
            }, 3000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}