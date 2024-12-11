package com.ecoliving.mobile.presentation.ui.activity.meals

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentMealsRecommBinding

class MealsRecommFragment : Fragment() {

    private var _binding: FragmentMealsRecommBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsRecommBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageRes = arguments?.getInt("imageRes")
        val text = arguments?.getString("text")
        val carbon = arguments?.getString("emission")
        val backgroundRes = arguments?.getInt("backgroundRes")

        binding.chosenMealsOption.setImageResource(
            imageRes ?: R.drawable.meals_40dp
        )

        binding.chosenMealsTitle.text = text
        binding.carbonEmission.text = carbon

        if (backgroundRes != null){
            binding.chosenMealsOption.setBackgroundResource(backgroundRes)
        }

        binding.saveButton.setOnClickListener{
            findNavController().navigate(R.id.action_mealsRecommFragment_to_dashboardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
