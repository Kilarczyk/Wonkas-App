package com.kamil.androidtest.ui.workersInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.kamil.androidtest.R
import com.kamil.androidtest.databinding.FragmentWorkersDetailedInfoBinding
import com.kamil.androidtest.util.AppConstants

class WorkerDetailedInfoFragment: Fragment() {

    private lateinit var workerViewModel: WorkerDetailedViewModel

    private lateinit var binding: FragmentWorkersDetailedInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_workers_detailed_info,
            container,
            false
        )

        workerViewModel = ViewModelProviders.of(this).get(WorkerDetailedViewModel::class.java)
        binding.workerRandomStringTxt.isSelected = true

        val workerId = arguments?.getInt(AppConstants.WORKER_ID_ARG) ?: 0

        binding.lifecycleOwner = this
        workerViewModel.fetchWorker(workerId)

        binding.btnDetailedInfoBack.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.viewModel = workerViewModel

        return binding.root
    }

}