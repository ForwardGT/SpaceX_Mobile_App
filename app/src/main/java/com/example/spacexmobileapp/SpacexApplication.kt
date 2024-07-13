package com.example.spacexmobileapp

import android.app.Application
import com.example.spacexmobileapp.presentation.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SpacexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpacexApplication)

            modules(viewModel)
        }
    }
}

val viewModel = module {
    viewModel {
        MainViewModel()
    }
}