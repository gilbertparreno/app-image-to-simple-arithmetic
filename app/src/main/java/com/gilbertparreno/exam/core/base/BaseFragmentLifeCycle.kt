package com.gilbertparreno.exam.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.viewbinding.ViewBinding
import com.gilbertparreno.exam.core.utils.ViewModelUtils
import javax.inject.Inject

abstract class BaseFragmentLifeCycle<VM : ViewModel, VB : ViewBinding> : BaseFragment<VB>(),
    LifecycleOwner {

    @Inject lateinit var viewModel: VM

    abstract fun observerChanges()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        viewModel = ViewModelProvider(
            ViewModelStore(),
            ViewModelUtils.createFor(viewModel)
        ).get(viewModel::class.java)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observerChanges()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }
}