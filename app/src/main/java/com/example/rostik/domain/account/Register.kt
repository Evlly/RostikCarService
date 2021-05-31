package com.example.rostik.domain.account

import com.example.rostik.domain.interactor.UseCase
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.Failure
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
    ) : UseCase<None, Register.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.F,
            params.I,
            params.O,
            params.car,
            params.login,
            params.password,
            params.phone)
    }

    data class Params(val F: String,
                      val I: String,
                      val O: String,
                      val car: String,
                      val login: String,
                      val password: String,
                      val phone: String)
}