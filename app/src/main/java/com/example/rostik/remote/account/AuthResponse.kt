package com.example.rostik.remote.account

import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.remote.core.BaseResponse

class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)