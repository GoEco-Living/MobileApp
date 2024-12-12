package com.ecoliving.mobile.presentation.ui.activity.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentTransportBinding

class TransportFragment : Fragment() {

    private var _binding: FragmentTransportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_transportFragment_to_addActivityMainFragment)
        }

        binding.carOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.car_svgrepo_com, getString(R.string.car_option), R.drawable.ic_circle_myeco)
        }

        binding.motorbikeOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.motorbike_svgrepo_com, getString(R.string.motorbike_option), R.drawable.ic_circle_water)
        }

        binding.publicTransportOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.transport_40dp, getString(R.string.public_transport_option), R.drawable.ic_circle_co2)
        }

        binding.walkOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.walk_svgrepo_com, getString(R.string.walk_option), R.drawable.circle_olive)
        }

        binding.bikeOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.bike, getString(R.string.bike_option), R.drawable.circle_orange)
        }
    }

    private fun navigateToTargetFragment(imageRes: Int, text: String, backgroundRes: Int) {
        val bundle = Bundle().apply {
            putInt("backgroundRes", backgroundRes)
            putInt("imageRes", imageRes)
            putString("text", text)
        }
        findNavController().navigate(R.id.action_transportFragment_to_detailsTransportActivityFragment, bundle)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}