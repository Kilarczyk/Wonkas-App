package com.kamil.androidtest.ui.workersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kamil.androidtest.R
import com.kamil.androidtest.data.model.Worker
import com.kamil.androidtest.databinding.LayoutWorkersListBinding

class WorkerAdapter: RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>() {

    lateinit var workersList: MutableList<Worker>

    var onItemClick: ((Worker) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerAdapter.WorkerViewHolder {
        val binding: LayoutWorkersListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_workers_list, parent, false)
        return WorkerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::workersList.isInitialized) workersList.size else 0
    }

    override fun onBindViewHolder(holder: WorkerAdapter.WorkerViewHolder, position: Int) {
        holder.bind(workersList[position])
    }

    fun updateWorkersList(workersList: MutableList<Worker>){
        this.workersList = workersList
        notifyDataSetChanged()
    }

    inner class WorkerViewHolder(private val binding: LayoutWorkersListBinding): RecyclerView.ViewHolder(binding.root){

        private val viewModel = WorkerViewModel()

        fun bind(worker: Worker){
            viewModel.bind(worker)
            binding.viewModel = viewModel

            binding.root.setOnClickListener {
                onItemClick?.invoke(worker)
            }
        }
    }
}