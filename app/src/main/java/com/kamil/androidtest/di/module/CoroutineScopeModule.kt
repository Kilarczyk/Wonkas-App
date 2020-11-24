package com.kamil.androidtest.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@Module
@Suppress("unused")
object CoroutineScopeModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCoroutineScope(coroutineContext: CoroutineContext): CoroutineScope{
        return CoroutineScope(coroutineContext)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCoroutineContext(): CoroutineContext{
        val parentJob = Job()
        val coroutineContext = parentJob + Dispatchers.IO
        return coroutineContext
    }
}