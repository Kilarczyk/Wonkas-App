package com.kamil.androidtest.api

import com.kamil.androidtest.data.model.Worker

class WorkersRepository(val api: WonkasApi): BaseRepository() {

    suspend fun getWorkersList(): MutableList<Worker>?{
        val workerResponse = safeApiCall(
            call = {api.getWorkers().await()},
            errorMessage = "Error Fetching Workers"
        )

        return workerResponse?.results?.toMutableList()
    }

    suspend fun getWorker(id: Int): Worker?{
        return safeApiCall(
            call = {api.getWorkerDetails(id).await()},
            errorMessage = "Error Fetching Worker by Id"
        )
    }
}