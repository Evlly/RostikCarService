package com.example.rostik.data.contracts

import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ContractsRepository
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.flatMap

class ContractsRepositoryImpl(private val contractsRemote: ContractsRemote): ContractsRepository {

    override fun contracts(id: Int): Either<Failure, List<ContractEntity>> {
       return contractsRemote.contracts(id)
    }

    override fun services(): Either<Failure, List<ServiceEntity>> {
        return contractsRemote.services()
    }

    override fun postServices(id: Int, date_start: String, id_price: ArrayList<Int>): Either<Failure, None> {
        return contractsRemote.postServices(id, date_start, id_price)
    }
}