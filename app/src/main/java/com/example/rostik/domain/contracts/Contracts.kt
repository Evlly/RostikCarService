package com.example.rostik.domain.contracts

import com.example.rostik.cache.SharedPrefsManager
import com.example.rostik.data.account.AccountRepositoryImpl
import com.example.rostik.domain.interactor.UseCase
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.onNext
import javax.inject.Inject

class Contracts @Inject constructor(private val contractsRepository: ContractsRepository): UseCase<List<ContractEntity>, Contracts.Params>() {



    override suspend fun run(params: Params) =
        contractsRepository.contracts(params.id)


    data class Params(val id: Int)
}