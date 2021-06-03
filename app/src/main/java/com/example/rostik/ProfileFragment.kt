package com.example.rostik

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rostik.databinding.FragmentProfileBinding
import com.example.rostik.domain.account.AccountEntity
import com.example.rostik.domain.type.None
import com.example.rostik.presentation.viewmodel.AccountViewModel
import com.example.rostik.ui.App
import com.example.rostik.ui.core.BaseFragment
import com.example.rostik.ui.core.ext.onFailure
import com.example.rostik.ui.core.ext.onSuccess

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var accountViewModel: AccountViewModel
    override lateinit var layout: View
    private lateinit var binding:FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        layout = binding.root
        App.appComponent.inject(this)
        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onSuccess(logoutData, ::handleLogout)
            onFailure(failureData, ::handleFailure) }

        accountViewModel.getAccount()

        binding.buttonLogout.setOnClickListener {
            accountViewModel.logout()
        }
    }

    private fun handleAccount(accountEntity: AccountEntity?) {
        accountEntity?.let {
            binding.tvProfile1.text = "${it.F}\n${it.I}\n" +
                    "${it.O}"
            binding.tvProfile2.text = it.login
            binding.tvProfile3.text = it.car
            binding.tvProfile4.text = it.phone
        }
    }

    private fun handleLogout(none: None?) {
        navigator.showLogin(requireContext())
        activity?.finish()
    }
}