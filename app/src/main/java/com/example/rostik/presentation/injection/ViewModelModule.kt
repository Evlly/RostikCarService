package com.example.rostik.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rostik.presentation.viewmodel.AccountViewModel
import com.example.rostik.presentation.viewmodel.ContractsViewModel
import com.example.rostik.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContractsViewModel::class)
    abstract fun bindContractsViewModel(contractsViewModel: ContractsViewModel): ViewModel

}