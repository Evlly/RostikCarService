package com.example.rostik.data.account

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure

interface AccountRemote {
    fun register(
        F: String,
        I: String,
        O: String,
        car: String,
        login: String,
        password: String,
        phone: String
    ): Either<Failure, None>
}