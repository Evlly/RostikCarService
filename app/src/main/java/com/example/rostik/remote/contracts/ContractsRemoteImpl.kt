package com.example.rostik.remote.contracts

import com.example.rostik.data.contracts.ContractsRemote
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.None
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ApiService
import javax.inject.Inject

class ContractsRemoteImpl @Inject constructor(
private val request: Request,
private val service: ApiService): ContractsRemote {

    override fun contracts(id: Int): Either<Failure, List<ContractEntity>> {
        return request.make(service.contracts(id)){it}
    }

    override fun services(): Either<Failure, List<ServiceEntity>> {
        return request.make(service.services()){it}
    }
}