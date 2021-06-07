package com.example.rostik.domain.contracts

import com.example.rostik.domain.interactor.UseCase
import javax.inject.Inject

class Services @Inject constructor(private val contractsRepository: ContractsRepository): UseCase<List<ServiceEntity>, Services.Params>() {

    override suspend fun run(params: Params) =
        contractsRepository.services()

    class Params()
}