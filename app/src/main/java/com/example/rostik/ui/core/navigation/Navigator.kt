package com.example.rostik.ui.core.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.rostik.presentation.Authenticator
import com.example.rostik.ui.register.RegisterActivity
import com.example.rostik.ui.home.HomeActivity
import com.example.rostik.ui.login.LoginActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showHome(context, false)
            false -> showLogin(context, false)
        }
    }

    fun showLogin(context: Context, newTask: Boolean = true) = context.startActivity<LoginActivity>(newTask = newTask)

    fun showHome(context: Context, newTask: Boolean = true) = context.startActivity<HomeActivity>(newTask = newTask)

    fun showSignUp(context: Context) = context.startActivity<RegisterActivity>()
}

private inline fun <reified T> Context.startActivity(newTask: Boolean = false, args: Bundle? = null) {
    this.startActivity(Intent(this, T::class.java).apply {
        if (newTask) {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        putExtra("args", args)
    })
}