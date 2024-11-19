package com.devon.hisaabkitaab.di

import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel {
        MainViewModel(get())
    }

}