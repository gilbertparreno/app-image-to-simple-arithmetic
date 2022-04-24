package com.gilbertparreno.exam.ui.main

import android.Manifest.permission.CAMERA
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.gilbertparreno.exam.ArithmeticApplication
import com.gilbertparreno.exam.BuildConfig
import com.gilbertparreno.exam.R
import com.gilbertparreno.exam.core.base.BaseFragmentLifeCycle
import com.gilbertparreno.exam.core.entities.FeatureType
import com.gilbertparreno.exam.core.entities.FeatureType.Camera
import com.gilbertparreno.exam.core.entities.FeatureType.FileSystem
import com.gilbertparreno.exam.core.entities.TaskStatus
import com.gilbertparreno.exam.core.extensions.getLastModifiedFile
import com.gilbertparreno.exam.core.extensions.launchWithUri
import com.gilbertparreno.exam.core.extensions.setDebounceClickListener
import com.gilbertparreno.exam.core.helpers.ToastHelper
import com.gilbertparreno.exam.databinding.FragmentMainBinding

class MainFragment : BaseFragmentLifeCycle<MainViewModel, FragmentMainBinding>() {

    private val cameraResult = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            val file = requireContext().filesDir.getLastModifiedFile()
            val bitmap = BitmapFactory.decodeFile(file?.path)
            viewModel.getTextFromBitmap(bitmap)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                ToastHelper.showSuccessToast(
                    requireContext(),
                    R.string.permission_granted_fail_message
                )
            }
        }

    override fun inject() {
        ArithmeticApplication.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding?.addInput?.setDebounceClickListener {
            routeByFeatureFlag()
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun observerChanges() {
        viewModel.resultEvent.observe(this) {
            when (it) {
                is TaskStatus.Loading -> {}
                is TaskStatus.SuccessWithResult -> {
                    viewBinding?.resultText?.text = it.result.toString()
                }
                is TaskStatus.Failure -> {
                    viewBinding?.resultText?.text = it.error.message
                    ToastHelper.showErrorToast(
                        requireContext(),
                        R.string.generic_error_message
                    )
                }
            }
        }
    }

    private fun routeByFeatureFlag() {
        when (FeatureType.getType(BuildConfig.FILE_SOURCE_TYPE)) {
            is Camera -> {
                val isPermissionGranted = hasPermission(
                    permission = CAMERA,
                    permissionResultLauncher = requestPermissionLauncher
                )
                if (isPermissionGranted) {
                    cameraResult.launchWithUri(requireContext())
                }
            }
            is FileSystem -> {

            }
        }
    }
}