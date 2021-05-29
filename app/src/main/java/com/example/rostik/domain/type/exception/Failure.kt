package com.example.rostik.domain.type.exception

sealed class Failure {
    object NetworkConnectionError: Failure()
    object ServerError: Failure()
    object NameAlreadyExistError : Failure()
}