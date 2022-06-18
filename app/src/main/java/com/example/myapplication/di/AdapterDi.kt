package com.example.myapplication.di

import com.example.myapplication.ui.main.MainAdapter
import org.koin.dsl.module

val AdapterModule = module {
    single {
        MainAdapter()
    }
}