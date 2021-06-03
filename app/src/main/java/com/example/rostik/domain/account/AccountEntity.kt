package com.example.rostik.domain.account

import com.google.gson.annotations.SerializedName

class AccountEntity(
    val id: Int,
    val F: String,
    val I: String,
    val O: String,
    val car: String,
    val login: String,
    val phone: String
)