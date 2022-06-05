package com.somekoder.clark.sample.use_cases

import com.somekoder.clark.domain.DataState
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun login(username: String, password: String) : Flow<DataState<Any, Any>>
}