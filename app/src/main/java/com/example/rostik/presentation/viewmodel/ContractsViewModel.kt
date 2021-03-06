package com.example.rostik.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.rostik.cache.SharedPrefsManager
import com.example.rostik.domain.contracts.*
import com.example.rostik.domain.type.None
import com.example.rostik.presentation.viewmodel.BaseViewModel
import javax.inject.Inject

class ContractsViewModel @Inject constructor(
    val contractsUseCase: Contracts,
    val servicesUseCase: Services,
    val postServicesUseCase: PostServices,
    private val sharedPrefsManager: SharedPrefsManager
)  : BaseViewModel() {


    val id = sharedPrefsManager.getIdInt()

    var contractsData: MutableLiveData<List<ContractEntity>> = MutableLiveData()

    var servicesData: MutableLiveData<List<ServiceEntity>> = MutableLiveData()

    var postServicesData: MutableLiveData<None> = MutableLiveData()

    fun contracts(){
        contractsUseCase(Contracts.Params(id)){
            it.either(::handleFailure, ::handleContracts)
        }
    }

    fun getContracts():List<ContractEntity>?{
        if (contractsData.value!=null){
            return contractsData.value
        }
        contracts()
        return emptyList()
    }

    fun handleContracts(list: List<ContractEntity>){
        this.contractsData.value = list
    }

    fun services(){
        servicesUseCase(Services.Params()){
            it.either(::handleFailure, ::handleServices)
        }
    }

    fun getServices():List<ServiceEntity>?{
        if (servicesData.value!=null){
            return servicesData.value
        }
        services()
        return emptyList()
    }

    fun handleServices(list: List<ServiceEntity>){
        this.servicesData.value = list
    }



    override fun onCleared() {
        super.onCleared()
        contractsUseCase.unsubscribe()
        servicesUseCase.unsubscribe()
    }

    fun postServices(date_start: String, id_price: ArrayList<Int>){
        postServicesUseCase(PostServices.Params(id, date_start, id_price)){
            it.either(::handleFailure, ::handlePost)
        }
    }

    fun handlePost(none: None){
        this.postServicesData.value = none
    }
}