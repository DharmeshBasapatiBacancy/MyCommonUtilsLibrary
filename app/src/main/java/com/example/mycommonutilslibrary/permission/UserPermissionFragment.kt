package com.example.mycommonutilslibrary.permission

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import com.example.mycommonutilslibrary.databinding.FragmentUserPermissionBinding
import com.example.ourbaseutils.alerts.showSettingsDialog
import com.example.ourbaseutils.permission.BasePermissionFragment
import java.io.ByteArrayOutputStream

class UserPermissionFragment : BasePermissionFragment() {

    private lateinit var binding: FragmentUserPermissionBinding

    override fun getSinglePermission(): String = android.Manifest.permission.CAMERA

    override fun getMultiplePermissions(): Array<String> = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserPermissionBinding.inflate(inflater, container, false)
        intMethod()
        return binding.root
    }

    private fun intMethod() {

        binding.btnSingleUserPermission.setOnClickListener {
            requestSinglePermission()
        }

        binding.btnMultiUserPermission.setOnClickListener {
            requestMultiplePermissions()
        }
    }

    override fun onSinglePermissionGranted() {
        super.onSinglePermissionGranted()
        // Your logic after single permission is granted
        takePicture()
    }

    override fun onMultiplePermissionsGranted() {
        super.onMultiplePermissionsGranted()
        // Your logic after multiple permission is granted
        takePicture()
    }

    override fun onPermissionResult(activityResult: ActivityResult?) {

        // Your logic after permission result using data
        val data = activityResult?.data
        val imageBitmap = data?.extras?.get("data") as? Bitmap

        if (imageBitmap != null) {
            val baos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)

            val byteArray = baos.toByteArray()
            val temp = Base64.encodeToString(byteArray, Base64.DEFAULT)

            // Add image
            val imageBytes = Base64.decode(temp, Base64.DEFAULT)
            val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

            // Set the image to ImageView
            binding.imgViewResult.setImageBitmap(image)
        }
    }

    override fun showPermissionRationaleDialog(permission: String) {
        // Open the app settings
        requireContext().showSettingsDialog(requireActivity())
    }

    private fun takePicture() {

        try {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // Launch the camera using the ActivityResultLauncher
            activityResultIntentLauncher.launch(takePictureIntent)
        } catch (e: Exception) {
            println(e)
        }
    }
}