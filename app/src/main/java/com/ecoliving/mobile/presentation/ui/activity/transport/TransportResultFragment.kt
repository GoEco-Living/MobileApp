package com.ecoliving.mobile.presentation.ui.activity.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.remote.response.AddTransportResponse
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentDetailsTransportActivityBinding
import com.example.ecoliving.databinding.FragmentTransportResultBinding

class TransportResultFragment : Fragment() {

    private var _binding: FragmentTransportResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransportResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRes = arguments?.getInt("imageRes")
        val text = arguments?.getString("text")
        val carbon = arguments?.getString("emission")
        val backgroundRes = arguments?.getInt("backgroundRes")

        binding.chosenTransportOption.setImageResource(
            imageRes ?: R.drawable.car_transport_navigation_svgrepo_com
        )

        binding.chosenTransportTitle.text = text

        binding.carbonEmission.text = carbon

        if (backgroundRes != null) {
            binding.chosenTransportOption.setBackgroundResource(backgroundRes)
        }

        binding.saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_transportResultFragment_to_dashboardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}