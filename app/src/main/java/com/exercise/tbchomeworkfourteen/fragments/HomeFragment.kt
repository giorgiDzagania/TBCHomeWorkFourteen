package com.exercise.tbchomeworkfourteen.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.exercise.tbchomeworkfourteen.adapter.ItemModelAdapter
import com.exercise.tbchomeworkfourteen.databinding.FragmentHomeBinding
import com.exercise.tbchomeworkfourteen.model.ModelInfo
import com.exercise.tbchomeworkfourteen.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    ItemModelAdapter.Listener {
    lateinit var homeAdapter: ItemModelAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    override fun setUp() {
        prepareRecyclerView()

        val initialItems = homeViewModel.models.value
        homeAdapter.submitList(initialItems)

        addNewMaleModel()
        addNewFemaleModel()
        refreshScreen()
    }

    private fun prepareRecyclerView() {
        homeAdapter = ItemModelAdapter(this)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = homeAdapter
        }
    }

    private fun refreshScreen() {
        binding.swipeRefresh.setOnRefreshListener {
            homeViewModel.firstToModels()
            homeAdapter.submitList(homeViewModel.models.value)
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun addNewMaleModel() {
        binding.btdAddModelOne.setOnClickListener {
            homeViewModel.addNewModelMan()
            homeAdapter.submitList(homeViewModel.models.value)
        }
    }

    private fun addNewFemaleModel() {
        binding.btnAddModelTwo.setOnClickListener {
            homeViewModel.addNewModelWoman()
            homeAdapter.submitList(homeViewModel.models.value)
        }
    }

    override fun deleteCurrentItem(model: ModelInfo) {
        homeViewModel.deleteModel(model)
        homeAdapter.submitList(homeViewModel.models.value)
    }

}