package com.somekoder.clark.sample.data_source.remote

import com.somekoder.clark.domain.NetworkResult
import com.somekoder.clark.sample.repository.RemoteDataSource
import kotlinx.coroutines.delay

class RemoteDataSourceImpl(
    // private val api: ApiService
) : RemoteDataSource {

    override suspend fun login(username: String, password: String): NetworkResult<Any, Any> {
        delay(1000)
        return if (username == "Bob" && password == "1234") {
            NetworkResult.Success("Login successful!")
        } else {
            NetworkResult.Error("Invalid username or password")
        }
    }
}