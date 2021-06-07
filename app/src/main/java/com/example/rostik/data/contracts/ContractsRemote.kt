package com.example.rostik.data.contracts

import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.None

interface ContractsRemote {
    fun contracts(id: Int): Either<Failure, List<ContractEntity>>

    fun services(): Either<Failure, List<ServiceEntity>>
}