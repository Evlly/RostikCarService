package com.example.rostik.domain.account

import com.example.rostik.domain.interactor.UseCase
import com.example.rostik.domain.type.None
import javax.inject.Inject

class GetAccount @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<AccountEntity, None>() {

    override suspend fun run(params: None) = accountRepository.getCurrentAccount()
}