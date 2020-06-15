package com.mobile.droidsOnRoids.repository

import com.mobile.droidsOnRoids.data.dataSource.local.LocalDataSource
import com.mobile.droidsOnRoids.data.dataSource.remote.RemoteDataSource

class Repository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) : IRepository {
    //todo make safeCall()
    override fun getMockedText() = localDataSource.getMockedText()
}