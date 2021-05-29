package com.example.rostik.cache

import com.example.rostik.data.account.AccountCache
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager) :
    AccountCache {

    override fun saveUser(id: String): Either<Failure, None> {
        return prefsManager.saveId(id)
    }

    override fun getUser(): Either.Right<String?> {
        return prefsManager.getId()
    }
}