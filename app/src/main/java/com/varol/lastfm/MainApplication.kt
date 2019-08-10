package com.varol.lastfm

import android.app.Application
import com.varol.lastfm.di.*
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin(
            this,
            listOf(
                appModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                databaseModule
            )
        )
    }
}