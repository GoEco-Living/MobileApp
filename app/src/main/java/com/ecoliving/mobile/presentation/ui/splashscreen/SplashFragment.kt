package com.ecoliving.mobile.presentation.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.ecoliving.mobile.presentation.ui.dashboard.DashboardViewModel
import com.example.ecoliving.R

class SplashFragment : Fragment() {

    private val viewModel by viewModels<DashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getSession().observe(requireActivity()) { user ->
            Handler().postDelayed({
                if(!user.isLogin){
                    findNavController().navigate(R.id.action_splashFragment_to_onboardFragment)
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_dashboardFragment)
                }
            }, 1000)
        }

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}