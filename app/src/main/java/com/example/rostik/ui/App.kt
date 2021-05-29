package com.example.rostik.ui

import android.app.Application
import com.example.rostik.presentation.injection.AppModule
import com.example.rostik.presentation.injection.CacheModule
import com.example.rostik.presentation.injection.RemoteModule
import com.example.rostik.presentation.injection.ViewModelModule
import com.example.rostik.ui.activity.RegisterActivity
import com.example.rostik.ui.fragment.RegisterFragment
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

    //fragments
    fun inject(fragment: RegisterFragment)



}