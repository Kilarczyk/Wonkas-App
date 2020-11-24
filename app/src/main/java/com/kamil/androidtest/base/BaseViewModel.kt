package com.kamil.androidtest.base

import androidx.lifecycle.ViewModel
import com.kamil.androidtest.di.component.AppComponent
import com.kamil.androidtest.di.component.DaggerAppComponent
import com.kamil.androidtest.di.module.CoroutineScopeModule
import com.kamil.androidtest.di.module.NetworkModule
import com.kamil.androidtest.ui.workersInfo.WorkerDetailedViewModel
import com.kamil.androidtest.ui.workersList.WorkerListViewModel


abstract class BaseViewModel: ViewModel() {

    private val injector: AppComponent = DaggerAppComponent
        .builder()
        .networkModule(NetworkModule)
        .coroutineScopeModule(CoroutineScopeModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is WorkerListViewModel -> injector.inject(this)
            is WorkerDetailedViewModel -> injector.inject(this)
        }
    }

}