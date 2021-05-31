package com.example.rostik.domain.account

import com.example.rostik.domain.interactor.UseCase
import javax.inject.Inject

class Login @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, Login.Params>() {

    override suspend fun run(params: Params)
    = accountRepository.login(params.login, params.password)

    data class Params(val login: String, val password: String)
}