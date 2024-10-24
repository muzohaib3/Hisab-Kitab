package com.devon.hisaabkitaab.di

import android.app.Application
import com.devon.hisaabkitaab.datasource.repo.GeneralRepository
import com.devon.hisaabkitaab.datasource.remote.ApiService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object InjectUtils : KoinComponent {
    /**
     * Get Single instance of Application Context
     */
    val appContext: Application by inject()

    /**
     * Get Single instance of Retrofit WEB API Service
     */
    val getRetrofit: ApiService by inject()

    /**
     * Get Single instance of GeneralRepository
     */
    val getGeneralRepository : GeneralRepository by inject()
}