package com.somekoder.clark.sample.repository

import com.somekoder.clark.domain.DataState
import com.somekoder.clark.domain.NetworkResult
import com.somekoder.clark.sample.use_cases.Repository
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : Repository {

    override fun login(username: String, password: String) = flow {
        when (val response = remoteDataSource.login(username, password)) {
            is NetworkResult.Success -> {
                // Maybe store token in cache?
                emit(DataState.Data(response.data))
            }
            is NetworkResult.Error -> {
                emit(DataState.Error(response.error))
            }
            is NetworkResult.Exception -> {
                emit(DataState.Exception(response.exception))
            }
        }
    }
}