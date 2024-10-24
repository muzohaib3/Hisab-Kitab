package com.devon.hisaabkitaab.di

import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    /**
     * The viewModel keyword helps declaring a factory instance of ViewModel. [MainViewModel]
     * This instance will be handled by internal ViewModelFactory
     * and reattach [MainViewModel] ViewModel instance if needed.
     */
    viewModel {
        MainViewModel(get())
    }
}