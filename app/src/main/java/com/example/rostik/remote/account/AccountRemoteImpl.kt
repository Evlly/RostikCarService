package com.example.rostik.remote.account

import com.example.rostik.data.account.AccountRemote
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.Failure
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ApiService
import com.google.gson.JsonObject
import org.json.JSONObject
import javax.inject.Inject
import kotlin.math.log

class AccountRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : AccountRemote {

    override fun register(
        F: String,
        I: String,
        O: String,
        car: String,
        login: String,
        password: String,
        phone: String
    ): Either<Failure, None> {
        return request.make(service.register(createRegisterObject(
            F,
            I,
            O,
            car,
            login,
            password,
            phone)
            ,
            )) { None() }
    }

    override fun login(login: String, password: String, id: String?): Either<Failure, AccountEntity> {
        return request.make(service.login(createLoginObject(login, password))) { it.user }
    }

    private fun createRegisterObject(
        F: String,
        I: String,
        O: String,
        car: String,
        login: String,
        password: String,
        phone: String
    ): JsonObject {

        val jsonObject = JsonObject()
        jsonObject.addProperty(ApiService.PARAM_F, F)
        jsonObject.addProperty(ApiService.PARAM_I, I)
        jsonObject.addProperty(ApiService.PARAM_O, O)
        jsonObject.addProperty(ApiService.PARAM_CAR, car)
        jsonObject.addProperty(ApiService.PARAM_LOGIN, login)
        jsonObject.addProperty(ApiService.PARAM_PASSWORD, password)
        jsonObject.addProperty(ApiService.PARAM_PHONE, phone)
        return jsonObject
    }

    private fun createLoginObject(login: String, password: String): JsonObject {
        val jsonObject = JsonObject()
        jsonObject.addProperty(ApiService.PARAM_LOGIN, login)
        jsonObject.addProperty(ApiService.PARAM_PASSWORD, password)

        return jsonObject
    }
}