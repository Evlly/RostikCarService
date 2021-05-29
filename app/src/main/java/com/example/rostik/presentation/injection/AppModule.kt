package com.example.rostik.presentation.injection

import android.content.Context
import com.example.rostik.data.account.AccountCache
import com.example.rostik.data.account.AccountRemote
import com.example.rostik.data.account.AccountRepositoryImpl
import com.example.rostik.domain.account.AccountRepository
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
}