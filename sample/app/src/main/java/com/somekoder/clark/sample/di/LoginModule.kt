package com.somekoder.clark.sample.di

import com.somekoder.clark.sample.data_source.remote.ApiService
import com.somekoder.clark.sample.data_source.remote.RemoteDataSourceImpl
import com.somekoder.clark.sample.repository.RemoteDataSource
import com.somekoder.clark.sample.repository.RepositoryImpl
import com.somekoder.clark.sample.use_cases.LoginUseCase
import com.somekoder.clark.sample.use_cases.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        // apiService: ApiService
    ) : RemoteDataSource {
        return RemoteDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource
    ) : Repository {
        return RepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        repository: Repository
    ) : LoginUseCase {
        return LoginUseCase(repository)
    }
}