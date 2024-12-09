package com.ecoliving.mobile.presentation.ui.activity.transport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.remote.response.AddTransportResponse
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentDetailsTransportActivityBinding

class DetailsTransportActivityFragment : Fragment() {

    private var _binding: FragmentDetailsTransportActivityBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<TransportViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private var imageRes: Int? = null
    private var text: String? = null
    private var backgroundRes: Int? = null


    private var currentValue: String = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsTransportActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailsTransportActivityFragment_to_transportFragment)
        }

        imageRes = arguments?.getInt("imageRes")
        text = arguments?.getString("text")
        backgroundRes = arguments?.getInt("backgroundRes")

        binding.transportOption.setImageResource(
            imageRes ?: R.drawable.car_transport_navigation_svgrepo_com
        )
        binding.optionTitle.text = text

        if (backgroundRes != null) {
            binding.transportOption.setBackgroundResource(backgroundRes!!)
        }

        binding.slider.setLabelFormatter { value ->
            value.toInt().toString()
        }

        binding.slider.addOnChangeListener { _, value, _ ->
            currentValue = value.toInt().toString()
            binding.chosenDistance.text = currentValue
        }

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            binding.continueButton.setOnClickListener {

                if (text != null) {
                    viewModel.addTransportActivity(user.userId, text!!, currentValue.toInt())
                }

                viewModel.addTransport.observe(viewLifecycleOwner) { result ->
                    when (result) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            navigateToNextFragment(result.data)
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun navigateToNextFragment(response: AddTransportResponse) {
        val bundle = Bundle().apply {
            putString("emission", response.transport?.predictedEmission.toString())
            imageRes?.let { putInt("imageRes", it) }
            backgroundRes?.let { putInt("backgroundRes", it) }
            putString("text", text)
        }
        findNavController().navigate(R.id.action_detailsTransportActivityFragment_to_transportResultFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}