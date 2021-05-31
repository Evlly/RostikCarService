package com.example.rostik.cache

import com.example.rostik.data.account.AccountCache
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.Failure
import javax.inject.Inject

class AccountCacheImpl @Inject constructor(private val prefsManager: SharedPrefsManager) :
    AccountCache {

    override fun saveUser(id: String): Either<Failure, None> {
        return prefsManager.saveId(id)
    }

    override fun getUser(): Either.Right<String?> {
        return prefsManager.getId()
    }

    override fun logout(): Either<Failure, None> {
        return prefsManager.removeAccount()
    }

    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        return prefsManager.getAccount()
    }

    override fun saveAccount(account: AccountEntity): Either<Failure, None> {
        return prefsManager.saveAccount(account)
    }
}