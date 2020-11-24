package com.kamil.androidtest.ui.workersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kamil.androidtest.R
import com.kamil.androidtest.databinding.FragmentWorkerListBinding
import com.kamil.androidtest.util.AppConstants
import com.kamil.androidtest.util.Gender

class WorkerListFragment: Fragment(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener{

    private lateinit var workersViewModel: WorkerListViewModel

    private lateinit var binding: FragmentWorkerListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_worker_list,
            container,
            false
        )

        workersViewModel = ViewModelProviders.of(this).get(WorkerListViewModel::class.java)

        binding.rvWorkersList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )

        workersViewModel.getWorkersList()

        binding.viewModel = workersViewModel

        setGenderFilter()
        initSearch()

        workersViewModel.workersListAdapter.onItemClick = {worker ->
            val bundle = bundleOf(AppConstants.WORKER_ID_ARG to worker.id)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_workersListFragment_to_workerDetailedInfoFragment, bundle)
        }
        return binding.root
    }

    private fun setGenderFilter(){
        binding.maleBttn.setOnClickListener {
            it.isSelected = !it.isSelected
            filterGenders()
        }
        binding.femaleBttn.setOnClickListener {
            it.isSelected = !it.isSelected
            filterGenders()
        }
    }

    private fun filterGenders(){
        if (binding.maleBttn.isChecked && !binding.femaleBttn.isChecked)
            workersViewModel.fetchWorkersByGender(Gender.Male)
        else if (!binding.maleBttn.isChecked && binding.femaleBttn.isChecked)
            workersViewModel.fetchWorkersByGender(Gender.Female)
        else if (!binding.maleBttn.isChecked && !binding.femaleBttn.isChecked)
            workersViewModel.fetchWorkersByGender(Gender.UNKNOWN)
        else workersViewModel.fetchWorkersByGender(Gender.All)
    }

    private fun initSearch() {
        binding.searchProfession.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (p0?.isBlank() == true) workersViewModel.reloadWorkers()
        else workersViewModel.fetchWorkersByProfession(p0)
        return binding.rvWorkersList.adapter != null
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0?.isBlank() == true) workersViewModel.reloadWorkers()
        else workersViewModel.fetchWorkersByProfession(p0)
        return binding.rvWorkersList.adapter != null
    }
}