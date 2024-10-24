package com.devon.hisaabkitaab.application

import android.app.Application
import com.devon.hisaabkitaab.di.NetworkModule
import com.devon.hisaabkitaab.di.RepositoryModule
import com.devon.hisaabkitaab.di.RoomModule
import com.devon.hisaabkitaab.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{

            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    ViewModelModule,
                    RoomModule
                )
            )

        }
    }
}