package com.mobile.droidsOnRoids.di

import com.mobile.droidsOnRoids.data.dataSource.local.LocalDataSource
import com.mobile.droidsOnRoids.data.dataSource.remote.RemoteDataSource
import com.mobile.droidsOnRoids.repository.Repository
import com.mobile.droidsOnRoids.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModules {
    val modules = listOf(
        viewModelModule,
        repositoryModule,
        dataSourceModule
    )
}

private val viewModelModule = module {
    viewModel { StartViewModel(get()) }
}

private val repositoryModule = module {
    single { Repository(get(), get()) }
}

private val dataSourceModule = module {
    single { LocalDataSource() }
    single { RemoteDataSource() }
}
