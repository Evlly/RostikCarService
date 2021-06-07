package com.example.rostik.domain.contracts

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.None

interface ContractsRepository {
    fun contracts(id: Int): Either<Failure, List<ContractEntity>>
    fun services(): Either<Failure, List<ServiceEntity>>
    fun postServices(id: Int, date_start: String, id_price: ArrayList<Int>): Either<Failure, None>
}