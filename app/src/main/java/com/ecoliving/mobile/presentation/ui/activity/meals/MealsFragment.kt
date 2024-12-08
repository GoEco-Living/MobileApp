package com.ecoliving.mobile.presentation.ui.activity.meals

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentMealsBinding
import kotlinx.coroutines.flow.collect

class MealsFragment : Fragment(R.layout.fragment_meals) {

    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MealsViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMealsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        setupMealButtons()

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_mealsFragment_to_addActivityMainFragment)
        }

//        observeViewModel()
    }

    private fun setupMealButtons() {
        val mealOptions = mapOf(
            binding.veganOption to "Vegan",
            binding.vegetarianOption to "Vegetarian",
            binding.fishOption to "Fish",
            binding.chickenOption to "Chicken",
            binding.beefOption to "Beef"
        )

        mealOptions.forEach { (button, type) ->
            button.setOnClickListener {
                sendMealData(type)
            }
        }
    }

    private fun sendMealData(type: String) {
        viewModel.sendMealsData(type, predictedEmission = true)
    }

//    private fun observeViewModel() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.mealsResponse.collect { response ->
//                response?.let {
//                    binding.tvResult.text = "Success: ${it.type} sent!"
//                }
//            }
//
//            viewModel.errorMessage.collect { error ->
//                error?.let {
//                    binding.tvResult.text = "Error: $error"
//                }
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
