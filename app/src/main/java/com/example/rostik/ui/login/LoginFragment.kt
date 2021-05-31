package com.example.rostik.ui.login

import android.os.Bundle
import android.view.View
import com.example.rostik.R
import com.example.rostik.databinding.FragmentLoginBinding
import com.example.rostik.databinding.FragmentRegisterBinding
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.presentation.viewmodel.AccountViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess

class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    override lateinit var layout: View
    override val titleToolbar = R.string.screen_login


    private lateinit var accountViewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        binding = FragmentLoginBinding.inflate(layoutInflater)
        layout = binding.root

        accountViewModel = viewModel {
            onSuccess(accountData, ::renderAccount)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            validateFields()
        }

        binding.btnRegister.setOnClickListener {
            activity?.let { navigator.showSignUp(it) }
        }
    }

    private fun validateFields() {
        hideSoftKeyboard()
        val allFields = arrayOf(binding.etEmail, binding.etPassword)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        if (allValid) {
            login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }
    }

    private fun login(email: String, password: String) {
        showProgress()
        accountViewModel.login(email, password)
    }

    private fun renderAccount(account: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }
}