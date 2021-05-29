package com.example.rostik.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rostik.R
import com.example.rostik.domain.type.exception.Failure
import com.example.rostik.ui.activity.BaseActivity
import com.example.rostik.ui.activity.base
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract var layout: View

    open val titleToolbar = R.string.app_name
    open val showToolbar = true



    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return layout
    }

    override fun onResume() {
        super.onResume()

        base {
            if (showToolbar) supportActionBar?.show() else supportActionBar?.hide()
            supportActionBar?.title = getString(titleToolbar)
        }
    }

    open fun onBackPressed() {}


    fun showProgress() = base { progressStatus(View.VISIBLE) }

    fun hideProgress() = base { progressStatus(View.GONE) }


    fun hideSoftKeyboard() = base { hideSoftKeyboard() }


    fun handleFailure(failure: Failure?) = base { handleFailure(failure) }

    fun showMessage(message: String) = base { showMessage(message) }


    inline fun base(block: BaseActivity.() -> Unit) {
        activity.base(block)
    }


    inline fun <reified T : ViewModel> viewModel(body: T.() -> Unit): T {
        val vm = ViewModelProvider(this, viewModelFactory).get(T::class.java)
        vm.body()
        return vm
    }

    fun close() = parentFragmentManager.popBackStack()
}