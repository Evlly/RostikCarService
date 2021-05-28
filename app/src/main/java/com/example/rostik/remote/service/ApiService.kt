package com.example.rostik.remote.service

import com.example.rostik.remote.core.BaseResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object {
        //methods
        const val REGISTER = "register" //add path for register

        //params
        const val PARAM_F = "f"
        const val PARAM_I = "i"
        const val PARAM_O = "o"
        const val PARAM_CAR = "car"
        const val PARAM_LOGIN = "login"
        const val PARAM_PASSWORD = "password"
        const val PARAM_PHONE = "phone"
    }

    @FormUrlEncoded
    @POST(REGISTER)
    fun register(@FieldMap params: Map<String, String>): Call<BaseResponse>
}