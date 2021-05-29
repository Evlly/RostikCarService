package com.example.rostik.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.rostik.domain.account.Register
import com.example.rostik.domain.type.None
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    val registerUseCase: Register
) : BaseViewModel() {

    var registerData: MutableLiveData<None> = MutableLiveData()

    fun register(F: String,
                 I: String,
                 O: String,
                 car: String,
                 login: String,
                 password: String,
                 phone: String) {
        registerUseCase(Register.Params(F, I, O, car, login, password, phone))
        { it.either(::handleFailure, ::handleRegister) }
    }

    private fun handleRegister(none: None) {
        this.registerData.value = none
    }

    override fun onCleared() {
        super.onCleared()
        registerUseCase.unsubscribe()
    }
}