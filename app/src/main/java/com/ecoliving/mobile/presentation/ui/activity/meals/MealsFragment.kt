package com.ecoliving.mobile.presentation.ui.activity.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentMealsBinding

class MealsFragment : Fragment() {

    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMealsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.veganOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.salad_svgrepo_com, getString(R.string.vegan_option), R.drawable.ic_circle_myeco)
        }

        binding.vegetarianOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.vegetarian_diet, getString(R.string.vegetarian_option), R.drawable.ic_circle_water)
        }

        binding.fishOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.fish, getString(R.string.fish_option), R.drawable.ic_circle_co2)
        }

        binding.chickenOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.chicken, getString(R.string.chicken_option), R.drawable.circle_olive)
        }

        binding.beefOption.setOnClickListener {
            navigateToTargetFragment(R.drawable.cow_7_svgrepo_com, getString(R.string.beef_option), R.drawable.circle_orange)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_mealsFragment_to_addActivityMainFragment)
        }
    }

    private fun navigateToTargetFragment(imageRes: Int, text: String, backgroundRes: Int) {
        val bundle = Bundle().apply {
            putInt("backgroundRes", backgroundRes)
            putInt("imageRes", imageRes)
            putString("text", text)
        }
        findNavController().navigate(R.id.action_mealsFragment_to_mealsRecommFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}