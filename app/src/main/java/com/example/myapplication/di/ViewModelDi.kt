package com.example.myapplication.di

import com.example.myapplication.ui.info.InfoViewModel
import com.example.myapplication.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        MainViewModel(get(), get())
    }
    viewModel{
        InfoViewModel(get())
    }
}