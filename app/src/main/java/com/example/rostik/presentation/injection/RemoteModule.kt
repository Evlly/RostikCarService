package com.example.rostik.presentation.injection

import com.example.rostik.BuildConfig
import com.example.rostik.data.account.AccountRemote
import com.example.rostik.remote.account.AccountRemoteImpl
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ApiService
import com.example.rostik.remote.service.ServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = ServiceFactory.makeService(BuildConfig.DEBUG)

    @Singleton
    @Provides
    fun provideAccountRemote(request: Request, apiService: ApiService): AccountRemote {
        return AccountRemoteImpl(request, apiService)
    }
}