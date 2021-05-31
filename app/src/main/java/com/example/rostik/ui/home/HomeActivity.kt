package com.example.rostik.ui.home

import android.os.Bundle
import android.view.View
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

class HomeActivity : BaseActivity() {

    override val fragment = ContractsFragment()

    override val contentId = R.layout.activity_home
    private lateinit var binding: ActivityHomeBinding

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            onFailure(failureData, ::handleFailure)
        }
        accountViewModel.getAccount()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }





}