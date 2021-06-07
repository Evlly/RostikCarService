package com.example.rostik.remote.contracts

import com.example.rostik.data.contracts.ContractsRemote
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import com.example.rostik.domain.type.None
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
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

    override fun postServices(
        id: Int,
        date_start: String,
        id_price: ArrayList<Int>
    ): Either<Failure, None> {
        return request.make(service.postServices(createPostServicesObject(id, date_start, id_price))){None()}
    }

    fun createPostServicesObject(
        id: Int,
        date_start: String,
        id_price: ArrayList<Int>): JsonObject{
        val serv: Serv
        val arrayList = ArrayList<Id>()
        id_price.forEach {
            arrayList.add(Id(it))
        }
        serv = Serv(id, date_start, arrayList)
        val gson = GsonBuilder().create()
        val jsonElement = gson.toJsonTree(serv)
        val jsonObject = jsonElement as JsonObject
        //val json = Gson().toJson(serv)
        //return json
        return jsonObject
    }

    data class Serv(val id_client: Int,
    val date_start: String,
    val id_price: ArrayList<Id>)

    data class Id(val id:Int)
}