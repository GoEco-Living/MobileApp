package com.ecoliving.mobile.presentation.ui.activity.addactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentAddActivityMainBinding

class AddActivityMainFragment : Fragment() {

    private var _binding: FragmentAddActivityMainBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModels<AddActivityViewModel> {
//        ViewModelFactory.getInstance(requireActivity())
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener{
            findNavController().navigate(R.id.action_addActivityMainFragment_to_dashboardFragment)
        }
        binding.buttonMeals.setOnClickListener{
            findNavController().navigate(R.id.action_addActivityMainFragment_to_mealsFragment)
        }
        binding.buttonTransport.setOnClickListener{
            findNavController().navigate(R.id.action_addActivityFragment_to_transportFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}