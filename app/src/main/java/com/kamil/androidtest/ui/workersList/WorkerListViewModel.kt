package com.kamil.androidtest.ui.workersList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kamil.androidtest.api.WorkersRepository
import com.kamil.androidtest.base.BaseViewModel
import com.kamil.androidtest.data.model.Worker
import com.kamil.androidtest.util.Gender
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkerListViewModel: BaseViewModel() {

    @Inject lateinit var scope: CoroutineScope

    @Inject lateinit var repository: WorkersRepository

    val workersLiveData = MutableLiveData<MutableList<Worker>>()
    val workersListAdapter: WorkerAdapter = WorkerAdapter()
    private lateinit var observer: Observer<MutableList<Worker>>

    init {
        loadWorkers()
    }

    private fun loadWorkers() {
        observer = Observer {
            workersListAdapter.updateWorkersList(it)
        }
        workersLiveData.observeForever(observer)
    }

    fun getWorkersList(){
        scope.launch {
            val workers = repository.getWorkersList()
            workersLiveData.postValue(workers)
        }
    }

    fun findWorker(workerName: String){

    }

    fun reloadWorkers(){
        workersListAdapter.updateWorkersList(workersLiveData.value ?: arrayListOf())
    }

    fun fetchWorkersByGender(gender: Gender){
        val workers = workersLiveData.value?.filter {
            when(gender){
                Gender.Male -> it.gender == "M"
                Gender.Female -> it.gender == "F"
                Gender.All -> it.gender == "F" || it.gender == "M"
                Gender.UNKNOWN -> it.gender == "U"
            }
        }?.toMutableList()

        workersListAdapter.updateWorkersList(workers ?: arrayListOf())
    }

    fun fetchWorkersByProfession(profession : String?){
        val workers = workersLiveData.value?.filter {
            it.profession?.contains(profession.toString(), true) ?: false
        }?.toMutableList()

        workersListAdapter.updateWorkersList(workers ?: arrayListOf())
    }

    override fun onCleared() {
        workersLiveData.removeObserver(observer)
        super.onCleared()
        cancelAllRequests()
    }

    fun cancelAllRequests() = scope.coroutineContext.cancel()
}