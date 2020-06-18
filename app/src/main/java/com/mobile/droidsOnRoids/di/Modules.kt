package com.mobile.droidsOnRoids.di

import com.mobile.droidsOnRoids.BuildConfig
import com.mobile.droidsOnRoids.common.*
import com.mobile.droidsOnRoids.data.dataSource.local.LocalDataSource
import com.mobile.droidsOnRoids.data.dataSource.remote.RemoteDataSource
import com.mobile.droidsOnRoids.data.network.SudokuResponseConverter
import com.mobile.droidsOnRoids.data.network.SudokuApi
import com.mobile.droidsOnRoids.database.AppDatabase
import com.mobile.droidsOnRoids.repository.SudokuRepository
import com.mobile.droidsOnRoids.ui.splashScreen.SplashScreenViewModel
import com.mobile.droidsOnRoids.ui.sudoku.SudokuViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModules {
    val modules = listOf(
        viewModelModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        databaseModule
    )
}

private val viewModelModule = module {
    viewModel { SudokuViewModel(get(named(SUDOKU_REPOSITORY))) }
    viewModel { SplashScreenViewModel() }
}

private val repositoryModule = module {
    single(named(SUDOKU_REPOSITORY)) {
        SudokuRepository(
            get(named(SUDOKU_LOCAL_DATA_SOURCE)), get(
                named(
                    SUDOKU_REMOTE_DATA_SOURCE
                )
            )
        )
    }
}

private val dataSourceModule = module {
    single(named(SUDOKU_LOCAL_DATA_SOURCE)) { LocalDataSource(get()) }
    single(named(SUDOKU_REMOTE_DATA_SOURCE)) { RemoteDataSource(get(named(SUDOKU_RETROFIT))) }
}

private val networkModule = module {
    single(named(SUDOKU_RETROFIT)) {
        Retrofit.Builder()
            .addConverterFactory(SudokuResponseConverter)
            .baseUrl(BuildConfig.BASE_URL)
            .client(get(named(SUDOKU_OK_HTTP)))
            .build()
            .create(SudokuApi::class.java)
    }

    single(named(SUDOKU_OK_HTTP)) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }
}

private val databaseModule = module {
    single { AppDatabase.buildDatabase(get()) }
}
