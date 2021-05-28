package com.example.rostik.domain.account

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure

interface AccountRepository {
    fun login(email: String, password: String): Either<Failure, AccountEntity>
    fun logout(): Either<Failure, None>
    fun register(F: String,
                 I: String,
                 O: String,
                 car: String,
                 login: String,
                 password: String,
                 phone: String): Either<Failure, None>

    fun getCurrentAccount(): Either<Failure, AccountEntity>

    fun editAccount(entity: AccountEntity): Either<Failure, AccountEntity>
}