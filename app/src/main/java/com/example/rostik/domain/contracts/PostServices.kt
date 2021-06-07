package com.example.rostik.domain.contracts

import com.example.rostik.domain.interactor.UseCase
import com.example.rostik.domain.type.None
import javax.inject.Inject

class PostServices @Inject constructor(private val contractsRepository: ContractsRepository): UseCase<None, PostServices.Params>() {


    override suspend fun run(params: PostServices.Params) =
        contractsRepository.postServices(params.id, params.date_start, params.id_price)



    data class Params(
        val id: Int,
        val date_start: String,
        val id_price: ArrayList<Int>
    )
}