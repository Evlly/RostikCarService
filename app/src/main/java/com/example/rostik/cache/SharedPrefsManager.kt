package com.example.rostik.cache

import android.content.SharedPreferences
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.exception.Failure
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_ID = "account_id"
    }

    fun saveId(id: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_ID, id)
        }.apply()

        return Either.Right(None())
    }

    fun getId(): Either.Right<String?> {
        return Either.Right(prefs.getString(ACCOUNT_ID, ""))
    }
}