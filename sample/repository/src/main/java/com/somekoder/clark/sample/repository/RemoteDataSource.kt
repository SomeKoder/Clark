package com.somekoder.clark.sample.repository

import com.somekoder.clark.domain.NetworkResult

interface RemoteDataSource {
    suspend fun login(username: String, password: String) : NetworkResult<Any, Any>
}