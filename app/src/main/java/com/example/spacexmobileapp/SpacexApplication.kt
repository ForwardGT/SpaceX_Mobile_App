package com.example.spacexmobileapp

import android.app.Application
import com.example.spacexmobileapp.data.repository.RepositoryImpl
import com.example.spacexmobileapp.domain.repository.SpacexRepository
import com.example.spacexmobileapp.presentation.crew.CrewScreenViewModel
import com.example.spacexmobileapp.presentation.galery.GalleryScreenViewModel
import com.example.spacexmobileapp.presentation.history.HistoryScreenViewModel
import com.example.spacexmobileapp.presentation.main.MainScreenViewModel
import com.example.spacexmobileapp.presentation.rocket.RocketScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SpacexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpacexApplication)

            modules(
                repository,
                historyScreenViewModel,
                rocketScreenViewModel,
                mainScreenViewModel,
                crewScreenViewModel,
                galleryScreenViewModel
            )
        }
    }
}


val historyScreenViewModel = module {
    viewModel { HistoryScreenViewModel() }
}

val rocketScreenViewModel = module {
    viewModel { RocketScreenViewModel() }
}

val mainScreenViewModel = module {
    viewModel { MainScreenViewModel() }
}

val crewScreenViewModel = module {
    viewModel { CrewScreenViewModel() }
}

val galleryScreenViewModel = module {
    viewModel { GalleryScreenViewModel() }
}

val repository = module {
    single<SpacexRepository> { RepositoryImpl() }
}