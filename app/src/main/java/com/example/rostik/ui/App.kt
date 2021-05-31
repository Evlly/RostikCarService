package com.example.rostik.ui

import android.app.Application
import com.example.rostik.presentation.injection.AppModule
import com.example.rostik.presentation.injection.CacheModule
import com.example.rostik.presentation.injection.RemoteModule
import com.example.rostik.presentation.injection.ViewModelModule
import com.example.rostik.ui.core.navigation.RouteActivity
import com.example.rostik.ui.home.ContractsFragment
import com.example.rostik.ui.home.HomeActivity
import com.example.rostik.ui.login.LoginFragment
import com.example.rostik.ui.register.RegisterActivity
import com.example.rostik.ui.register.RegisterFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}

@Singleton
@Component(modules = [AppModule::class, CacheModule::class, RemoteModule::class, ViewModelModule::class])
interface AppComponent {

    //activities
    fun inject(activity: RegisterActivity)

    fun inject(activity: RouteActivity)
    fun inject(activity: HomeActivity)

    //fragments
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ContractsFragment)



}