package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {

    companion object {
        lateinit var instance: MainApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp) // знадобиться, коли об'єкт потребує контекст застосунку
            androidLogger(Level.DEBUG)  //  рівень помилок
            modules(listOf(
                viewModelModule,
                serviceModule,
                userRepositoryModule,
                AdapterModule,
                databaseModule))
        }
    }

}