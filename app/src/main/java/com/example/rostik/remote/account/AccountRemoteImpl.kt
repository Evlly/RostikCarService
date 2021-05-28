package com.example.rostik.remote.account

import com.example.rostik.data.account.AccountRemote
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ApiService
import javax.inject.Inject

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
        return request.make(service.register(createRegisterMap(
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

    private fun createRegisterMap(
        F: String,
        I: String,
        O: String,
        car: String,
        login: String,
        password: String,
        phone: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_F, F)
        map.put(ApiService.PARAM_I, I)
        map.put(ApiService.PARAM_O, O)
        map.put(ApiService.PARAM_CAR, car)
        map.put(ApiService.PARAM_LOGIN, login)
        map.put(ApiService.PARAM_PASSWORD, password)
        map.put(ApiService.PARAM_PHONE, phone)
        return map
    }
}