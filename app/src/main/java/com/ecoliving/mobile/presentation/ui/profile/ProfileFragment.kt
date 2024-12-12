package com.ecoliving.mobile.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            val capitalizedUsername = user.name.capitalize()
            binding.apply {
                usernameUser.text = capitalizedUsername
                emailProfile.text = user.email
            }

        }
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
        }

        viewModel.logoutStatus.observe(viewLifecycleOwner) { success ->
            if (success) {
                findNavController().navigate(R.id.action_profileFragment_to_onboardFragment)
            } else {
                Toast.makeText(context, "Logout failed. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_dashboardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}