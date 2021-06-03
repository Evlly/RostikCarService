package com.example.rostik.remote.account

import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.remote.core.BaseResponse

class AuthResponse(
    error: String = "",
    message: String,
    val user: AccountEntity
) : BaseResponse(error, message)