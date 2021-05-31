package com.example.rostik

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rostik.cache.AccountCacheImpl
import com.example.rostik.cache.SharedPrefsManager
import com.example.rostik.data.account.AccountRepositoryImpl
import com.example.rostik.domain.account.AccountRepository
import com.example.rostik.domain.account.Register
import com.example.rostik.remote.account.AccountRemoteImpl
import com.example.rostik.remote.core.NetworkHandler
import com.example.rostik.remote.core.Request
import com.example.rostik.remote.service.ServiceFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)

        val accountCache = AccountCacheImpl(SharedPrefsManager(sharedPrefs))

        val accRemote = AccountRemoteImpl(Request(NetworkHandler(this)),
            ServiceFactory.makeService(true))

        val accountRepository: AccountRepository
        = AccountRepositoryImpl(accRemote, accountCache)

        accountCache.saveUser("0")

        val register = Register(accountRepository)
        register(Register.Params("aba","aba","abab","caca","ds","ds","3232")) {
            it.either({
                Toast.makeText(this, it.javaClass.simpleName, Toast.LENGTH_SHORT).show()
            }, {
                Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show()
            })
        }
    }
}