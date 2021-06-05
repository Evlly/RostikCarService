package com.example.rostik.ui.core.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.rostik.domain.contracts.ContractEntity
import com.example.rostik.domain.type.HandleOnce
import com.example.rostik.domain.type.Failure
import kotlin.reflect.KFunction1

fun <T : Any, L : LiveData<T>> LifecycleOwner.onSuccess(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<HandleOnce<Failure>>> LifecycleOwner.onFailure(liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(this, Observer {
        it.getContentIfNotHandled()?.let(body)
    })