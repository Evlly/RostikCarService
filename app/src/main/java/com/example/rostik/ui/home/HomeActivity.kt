package com.example.rostik.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rostik.R
import com.example.rostik.databinding.ActivityHomeBinding
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.None
import com.example.rostik.presentation.viewmodel.AccountViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.core.BaseActivity
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolb.toolbar)
        App.appComponent.inject(this)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_contracts,
            R.id.navigation_entry,
            R.id.navigation_profile,
            R.id.navigation_info))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        accountViewModel = viewModel {
        }
        accountViewModel.getAccount()



    }


    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProvider(this, viewModelFactory).get(T::class.java)
        vm.body()
        return vm
    }




}