package com.example.rostik.data.account

import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.account.AccountRepository
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure
import com.example.rostik.domain.type.flatMap
import java.util.*

class AccountRepositoryImpl(
    private val accountRemote: AccountRemote,
    private val accountCache: AccountCache
) : AccountRepository {

    override fun login(login: String, password: String): Either<Failure, AccountEntity> {
        throw UnsupportedOperationException("Login is not supported")
    }

    override fun logout(): Either<Failure, None> {
        throw UnsupportedOperationException("Logout is not supported")
    }

    override fun register(F: String,
                          I: String,
                          O: String,
                          car: String,
                          login: String,
                          password: String,
                          phone: String): Either<Failure, None> {
        return accountCache.getUser().flatMap {
            accountRemote.register(F, I, O, car, login, password, phone)
        }
    }



    override fun getCurrentAccount(): Either<Failure, AccountEntity> {
        throw UnsupportedOperationException("Get account is not supported")
    }


    override fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity> {
        throw UnsupportedOperationException("Editing account is not supported")
    }
}