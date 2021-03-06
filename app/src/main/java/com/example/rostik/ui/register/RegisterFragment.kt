package com.example.rostik.ui.register

import android.os.Bundle
import android.view.View
import com.example.rostik.R
import com.example.rostik.databinding.FragmentRegisterBinding
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.None
import com.example.rostik.presentation.viewmodel.AccountViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess

class RegisterFragment : BaseFragment() {
    override lateinit var layout: View

    override val titleToolbar = R.string.register

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        layout = binding.root
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(registerData, ::handleRegister)
            onSuccess(accountData, ::handleLogin)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewMembership.setOnClickListener {
            register()
        }
        binding.btnAlreadyHaveAkk.setOnClickListener {
            activity?.finish()
        }
    }

    private fun validateFields(): Boolean {
        val allFields = arrayOf(
            binding.etF,
            binding.etI,
            binding.etO,
            binding.etCar,
            binding.etEmail,
            binding.etPassword,
            binding.etConfirmPassword,
            binding.etPhone)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        return allValid && validatePasswords()
    }

    private fun validatePasswords(): Boolean {
        val valid = binding.etPassword.text.toString() == binding.etConfirmPassword.text.toString()
        if (!valid) {
            showMessage(getString(R.string.error_password_mismatch))
        }
        return valid
    }

    private fun register() {
        hideSoftKeyboard()

        val allValid = validateFields()

        if (allValid) {
            showProgress()

            accountViewModel.register(
                binding.etF.text.toString(),
                binding.etI.text.toString(),
                binding.etO.text.toString(),
                binding.etCar.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etPhone.text.toString(),
            )
        }
    }

    private fun handleLogin(accountEntity: AccountEntity?) {
        hideProgress()
        activity?.let {
            navigator.showHome(it)
            it.finish()
        }
    }

    private fun handleRegister(none: None? = None()) {
        accountViewModel.login(binding.etEmail.text.toString(),
            binding.etPassword.text.toString())
    }
}