package com.example.rostik.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.rostik.cache.SharedPrefsManager
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.contracts.Contracts
import com.example.rostik.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class ContractsViewModel @Inject constructor(
    val contractsUseCase: Contracts,
    private val sharedPrefsManager: SharedPrefsManager
)  : BaseViewModel() {


    val id = sharedPrefsManager.getIdInt()

    var contractsData: MutableLiveData<List<ContractEntity>> = MutableLiveData()

    fun contracts(){
        contractsUseCase(Contracts.Params(id)){
            it.either(::handleFailure, ::handleContracts)
        }
    }

    fun handleContracts(list: List<ContractEntity>){
        this.contractsData.value = list
    }

    override fun onCleared() {
        super.onCleared()
        contractsUseCase.unsubscribe()
    }
}