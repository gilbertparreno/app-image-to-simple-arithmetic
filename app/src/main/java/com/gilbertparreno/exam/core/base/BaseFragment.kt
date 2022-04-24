package com.gilbertparreno.exam.core.base

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gilbertparreno.exam.R
import com.gilbertparreno.exam.core.helpers.ToastHelper

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var viewBinding: VB? = null

    abstract fun inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inject()
        viewBinding = inflateViewBinding(inflater, container)
        return viewBinding?.root
    }

    abstract fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelStore.clear()
    }

    open fun hasPermission(
        permission: String,
        permissionResultLauncher: ActivityResultLauncher<String>? = null
    ): Boolean {
        return when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                permission
            ) == PERMISSION_GRANTED -> true
            shouldShowRequestPermissionRationale(permission) -> {
                ToastHelper.showErrorToast(
                    context = requireContext(),
                    text = getString(R.string.permission_rationale_message)
                        .format(permission.replace("android.permission.", ""))
                )
                return false
            }
            else -> {
                permissionResultLauncher?.launch(permission)
                return false
            }
        }
    }
}