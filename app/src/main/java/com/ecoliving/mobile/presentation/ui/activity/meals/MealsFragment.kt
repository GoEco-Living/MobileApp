package com.ecoliving.mobile.presentation.ui.activity.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.remote.response.MealsResponse
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.ecoliving.mobile.presentation.ui.activity.transport.TransportViewModel
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentMealsBinding

class MealsFragment : Fragment() {

    private var _binding: FragmentMealsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MealsViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.veganOption.setOnClickListener {
            fetchDataAndNavigate(R.drawable.salad_svgrepo_com, getString(R.string.vegan_option), R.drawable.ic_circle_myeco)
        }

        binding.vegetarianOption.setOnClickListener {
            fetchDataAndNavigate(R.drawable.vegetarian_diet, getString(R.string.vegetarian_option), R.drawable.ic_circle_water)
        }

        binding.fishOption.setOnClickListener {
            fetchDataAndNavigate(R.drawable.fish, getString(R.string.fish_option), R.drawable.ic_circle_co2)
        }

        binding.chickenOption.setOnClickListener {
            fetchDataAndNavigate(R.drawable.chicken, getString(R.string.chicken_option), R.drawable.circle_olive)
        }

        binding.beefOption.setOnClickListener {
            fetchDataAndNavigate(R.drawable.cow_7_svgrepo_com, getString(R.string.beef_option), R.drawable.circle_orange)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_mealsFragment_to_addActivityMainFragment)
        }
    }

    private fun fetchDataAndNavigate(imageResId: Int, optionText: String, circleResId: Int) {
        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            viewModel.addMealsActivity(user.userId, optionText)

            viewModel.addMeals.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        navigateToTargetFragment(imageResId, optionText, circleResId, result.data)
                    }

                    is Result.Error -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun navigateToTargetFragment(imageRes: Int, text: String, backgroundRes: Int, responseData: MealsResponse) {
        val bundle = Bundle().apply {
            putInt("backgroundRes", backgroundRes)
            putInt("imageRes", imageRes)
            putString("text", text)
            putString("emission", responseData.meal?.predictedEmission.toString())
        }
        findNavController().navigate(R.id.action_mealsFragment_to_mealsRecommFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}