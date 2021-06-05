package com.example.rostik.remote.core

import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.Failure
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Request @Inject constructor(private val networkHandler: NetworkHandler) {

    fun <T : Any, R> make(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return when (networkHandler.isConnected) {
            true -> execute(call, transform)
            false, null -> Either.Left(Failure.NetworkConnectionError)
        }
    }

    private fun <T : Any, R> execute(call: Call<T>, transform: (T) -> R): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSucceed()) {
                true -> Either.Right(transform((response.body()!!)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}

fun <T : Any> Response<T>.isSucceed(): Boolean {
    return isSuccessful && body() != null
}

fun <T : BaseResponse> Response<T>.parseError(): Failure {
    val message = (body() as BaseResponse).error
    return when (message) {
        "Пользователь с таким логином существует" -> Failure.NameAlreadyExistError
        "Неправильный логин или пароль" -> Failure.AuthError

        else -> Failure.ServerError
    }
}