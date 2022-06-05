package com.somekoder.clark.sample.data_source.remote

import com.somekoder.clark.data_source.retrofit_ext.call_adapter.NetworkResponse

interface ApiService {
    suspend fun login(username: String, password: String) : NetworkResponse<Any, Any>
}