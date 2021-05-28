package com.example.rostik.data.account

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure

interface AccountCache {
    fun getUser(): Either<Failure, String>
    fun saveUser(/*some fields*/): Either<Failure, None>
}