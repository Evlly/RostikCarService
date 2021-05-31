package com.example.rostik.domain.type

sealed class Failure {
    object NetworkConnectionError: Failure()
    object ServerError: Failure()
    object NameAlreadyExistError : Failure()
    object AuthError : Failure()
    object NoSavedAccountsError : Failure()
}