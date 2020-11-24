package com.kamil.androidtest.di.component

import com.kamil.androidtest.di.module.CoroutineScopeModule
import com.kamil.androidtest.di.module.NetworkModule
import com.kamil.androidtest.ui.workersInfo.WorkerDetailedViewModel
import com.kamil.androidtest.ui.workersList.WorkerListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (CoroutineScopeModule::class)])
interface AppComponent {

    fun inject(workerListViewModel: WorkerListViewModel)
    fun inject(workerDetailedViewModel: WorkerDetailedViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        fun networkModule(networkModule: NetworkModule): Builder
        fun coroutineScopeModule(coroutineScopeModule: CoroutineScopeModule): Builder
    }

}