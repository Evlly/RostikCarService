package com.example.rostik.data.account

import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.Failure

interface AccountCache {
    fun getUser(): Either<Failure, String?>
    fun saveUser(id: String): Either<Failure, None>

    fun logout(): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>
    fun saveAccount(account: AccountEntity): Either<Failure, None>
}