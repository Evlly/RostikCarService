package com.example.rostik.presentation.injection

import android.content.Context
import com.example.rostik.data.account.AccountCache
import com.example.rostik.data.account.AccountRemote
import com.example.rostik.data.account.AccountRepositoryImpl
import com.example.rostik.data.contracts.ContractsRemote
import com.example.rostik.data.contracts.ContractsRepositoryImpl
import com.example.rostik.domain.account.AccountRepository
import com.example.rostik.domain.contracts.ContractsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Provides
    @Singleton
    fun provideAccountRepository(remote: AccountRemote, cache: AccountCache): AccountRepository {
        return AccountRepositoryImpl(remote, cache)
    }

    @Provides
    @Singleton
    fun provideContractsRepository(remote: ContractsRemote): ContractsRepository {
        return ContractsRepositoryImpl(remote)
    }
}