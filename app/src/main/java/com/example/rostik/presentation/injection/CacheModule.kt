package com.example.rostik.presentation.injection

import android.content.Context
import android.content.SharedPreferences
import com.example.rostik.cache.AccountCacheImpl
import com.example.rostik.cache.SharedPrefsManager
import com.example.rostik.data.account.AccountCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAccountCache(prefsManager: SharedPrefsManager): AccountCache = AccountCacheImpl(prefsManager)
}