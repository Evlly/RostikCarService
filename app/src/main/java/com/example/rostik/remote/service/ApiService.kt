package com.example.rostik.remote.service

import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.ServiceEntity
import com.example.rostik.remote.account.AuthResponse
import com.example.rostik.remote.contracts.ContractsResponse
import com.example.rostik.remote.core.BaseResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    companion object {
        //methods
        const val REGISTER = "register"
        const val LOGIN = "login"
        const val CONTRACTS = "contracts"
        const val SERVICES = "all_services"

        //params
        const val PARAM_F = "F"
        const val PARAM_I = "I"
        const val PARAM_O = "O"
        const val PARAM_CAR = "car"
        const val PARAM_LOGIN = "login"
        const val PARAM_PASSWORD = "password"
        const val PARAM_PHONE = "phone"
        const val PARAM_USER_ID = "id"
    }

    @POST(REGISTER)
    fun register(@Body json: JsonObject): Call<BaseResponse>


    @POST(LOGIN)
    fun login(@Body json: JsonObject): Call<AuthResponse>

    @GET(CONTRACTS+"/{id}")
    fun contracts(@Path("id") id: Int): Call<List<ContractEntity>>

    @POST(CONTRACTS)
    fun postServices(@Body json: JsonObject): Call<ContractsResponse>

    @GET(SERVICES)
    fun services(): Call<List<ServiceEntity>>
}