package com.ecoliving.mobile.presentation.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecoliving.mobile.data.Result
import com.ecoliving.mobile.data.remote.response.DashboardResponse
import com.ecoliving.mobile.data.remote.response.MealsItem
import com.ecoliving.mobile.data.remote.response.MealsRecommResponse
import com.ecoliving.mobile.data.remote.response.TransportItem
import com.ecoliving.mobile.data.remote.response.TransportRecommResponse
import com.ecoliving.mobile.presentation.ViewModelFactory
import com.ecoliving.mobile.presentation.ui.dashboard.adapter.MealsAdapter
import com.ecoliving.mobile.presentation.ui.dashboard.adapter.TransportAdapter
import com.example.ecoliving.R
import com.example.ecoliving.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DashboardViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var transportsAdapter: TransportAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealsAdapter = MealsAdapter(emptyList())
        transportsAdapter = TransportAdapter(emptyList())

        binding.rvMeals.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = mealsAdapter
            setHasFixedSize(true)
        }

        binding.rvTransport.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = transportsAdapter
            setHasFixedSize(true)
        }

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            val capitalizedUsername = user.name.capitalize()
            binding.dashboardUsername.text = capitalizedUsername

            viewModel.getTotalCarbon(user.userId)
            viewModel.dashboardUser.observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }

                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            setTotalCarbon(result.data)
                        }
                    }
                }

                viewModel.getMealsHistory(user.userId)
                viewModel.listMeals.observe(viewLifecycleOwner) { meals ->
                    when (meals) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            setMealsHistory(meals.data)
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }

                viewModel.getTransportHistory(user.userId)
                viewModel.listTransport.observe(viewLifecycleOwner) { transport ->
                    when (transport) {
                        is Result.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            setTransportHistory(transport.data)
                        }

                        is Result.Error -> {
                            binding.progressBar.visibility = View.GONE
                        }
                    }
                }

                viewModel.getMealsRecommend(user.userId)
                viewModel.mealsRecommend.observe(viewLifecycleOwner) { mealsRecomm ->
                    if (mealsRecomm != null) {
                        when (mealsRecomm) {
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                            }

                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                setMealsRecommend(mealsRecomm.data)
                            }
                        }
                    }
                }

                viewModel.getTransportRecommend(user.userId)
                viewModel.transportRecommend.observe(viewLifecycleOwner) { transportRecomm ->
                    if (transportRecomm != null) {
                        when (transportRecomm) {
                            is Result.Error -> {
                                binding.progressBar.visibility = View.GONE
                            }

                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                            }

                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                setTransportRecommend(transportRecomm.data)
                            }
                        }
                    }
                }
            }
        }

        binding.logoProfile.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }

        binding.startCalculatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_addActivityMainFragment)
        }
    }

    private fun setTransportRecommend(recommend: TransportRecommResponse) {
        binding.transportTipsText.text = recommend.transportRecommendation
    }

    private fun setMealsRecommend(recommend: MealsRecommResponse) {
        binding.mealsTipsText.text = recommend.mealRecommendation
    }

    private fun setTotalCarbon(total: DashboardResponse) {
        binding.totalCarbon.text = total.totalCarbonEmission
    }

    private fun setMealsHistory(mealsList: List<MealsItem>) {
        mealsAdapter.updateMealsList(mealsList)
    }

    private fun setTransportHistory(transportList: List<TransportItem>) {
        transportsAdapter.updateTransportList(transportList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}