package com.kamil.androidtest.api

import com.kamil.androidtest.data.model.Worker
import com.kamil.androidtest.data.model.WorkerResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WonkasApi {
    @GET("/napptilus/oompa-loompas?page=1")
    fun getWorkers(): Deferred<Response<WorkerResponse>>

    @GET("/napptilus/oompa-loompas/{id}")
    fun getWorkerDetails(@Path("id") id: Int): Deferred<Response<Worker>>
}