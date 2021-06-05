package com.example.rostik.domain.contracts

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure

interface ContractsRepository {
    fun contracts(id: Int): Either<Failure, List<ContractEntity>>
}