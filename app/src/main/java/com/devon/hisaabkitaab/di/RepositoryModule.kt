package com.devon.hisaabkitaab.di


import com.devon.hisaabkitaab.datasource.repo.GeneralRepository
import org.koin.dsl.module

val RepositoryModule = module {

    /**
     * Define a Singleton of General Repository
     * Get Single instance of General Repository
     */
    single {
        GeneralRepository(get())
    }
}