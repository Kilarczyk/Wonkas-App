package com.kamil.androidtest.ui.workersInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kamil.androidtest.api.WorkersRepository
import com.kamil.androidtest.base.BaseViewModel
import com.kamil.androidtest.data.model.Worker
import com.kamil.androidtest.ui.workersList.WorkerViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class WorkerDetailedViewModel: BaseViewModel() {
    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var repository: WorkersRepository

    val currentWorkerLiveData= MutableLiveData<Worker>()

    private lateinit var  observer: Observer<Worker>

    val workerViewModel: WorkerViewModel = WorkerViewModel()

    init {
        loadWorker()
    }

    private fun loadWorker() {
        observer = Observer {
            workerViewModel.bind(it)
        }

        currentWorkerLiveData.observeForever(observer)
    }

    fun fetchWorker(id: Int) {
        scope.launch {
            val worker = repository.getWorker(id)
            worker?.id = id
            currentWorkerLiveData.postValue(worker)
        }
    }

    override fun onCleared() {
        currentWorkerLiveData.removeObserver(observer)
        super.onCleared()
        cancelAllRequests()
    }

    fun cancelAllRequests() = scope.coroutineContext.cancel()

}