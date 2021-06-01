package com.example.rostik.cache

import android.content.SharedPreferences
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.Either
import com.example.rostik.domain.type.None
import com.example.rostik.domain.type.Failure
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {
    companion object {
        const val ACCOUNT_ID = "account_id"
        const val ACCOUNT_IDS = "account_ids"
        const val ACCOUNT_F = "account_f"
        const val ACCOUNT_I = "account_i"
        const val ACCOUNT_O = "account_o"
        const val ACCOUNT_CAR = "account_car"
        const val ACCOUNT_LOGIN = "account_login"
        const val ACCOUNT_PHONE = "account_phone"
    }

    fun saveId(id: String): Either<Failure, None> {
        prefs.edit().apply {
            putString(ACCOUNT_IDS, id)
        }.apply()

        return Either.Right(None())
    }

    fun getId(): Either.Right<String?> {
        return Either.Right(prefs.getString(ACCOUNT_IDS, ""))
    }

    fun saveAccount(account: AccountEntity): Either<Failure, None> {
        prefs.edit().apply {
            putInt(ACCOUNT_ID, account.id)
            putString(ACCOUNT_F, account.F)
            putString(ACCOUNT_I, account.I)
            putString(ACCOUNT_O, account.O)
            putString(ACCOUNT_CAR, account.car)
            putString(ACCOUNT_LOGIN, account.login)
            putString(ACCOUNT_PHONE, account.phone)
        }.apply()

        return Either.Right(None())
    }

    fun getAccount(): Either<Failure, AccountEntity> {
        val id = prefs.getInt(ACCOUNT_ID, 0)

        if (id == 0) {
            return Either.Left(Failure.NoSavedAccountsError)
        }

        val account = AccountEntity(
            prefs.getInt(ACCOUNT_ID, 0),
            prefs.getString(ACCOUNT_F, "")!!,
            prefs.getString(ACCOUNT_I, "")!!,
            prefs.getString(ACCOUNT_O, "")!!,
            prefs.getString(ACCOUNT_CAR, "")!!,
            prefs.getString(ACCOUNT_LOGIN, "")!!,
            prefs.getString(ACCOUNT_PHONE, "")!!
        )

        return Either.Right(account)
    }

    fun removeAccount(): Either<Failure, None> {
        prefs.edit().apply {
            remove(ACCOUNT_ID)
            remove(ACCOUNT_F)
            remove(ACCOUNT_I)
            remove(ACCOUNT_O)
            remove(ACCOUNT_CAR)
            remove(ACCOUNT_LOGIN)
            remove(ACCOUNT_PHONE)
        }.apply()

        return Either.Right(None())
    }

    fun containsAnyAccount(): Boolean {
        val id = prefs.getInt(ACCOUNT_ID, 0)
        return id != 0
    }
}