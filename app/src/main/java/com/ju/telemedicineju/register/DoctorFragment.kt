package com.ju.telemedicineju.register

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentDoctorBinding
import com.ju.telemedicineju.databinding.FragmentStartBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DoctorFragment : Fragment() {


    private var _binding: FragmentDoctorBinding? = null
    private val binding get() = _binding!!

    private var outputUri: Uri? = null

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        when {
            result.isSuccessful -> {
                handleCropImageResult(result.uriContent.toString())
            }
            result is CropImage.CancelledResult -> showErrorMessage("cropping image was cancelled by the user")
            else -> showErrorMessage("cropping image failed")
        }
    }

    private val customCropImage = registerForActivityResult(CropImageContract()) {
        if (it !is CropImage.CancelledResult) {
            handleCropImageResult(it.uriContent.toString())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.takePhoto.setOnClickListener {
            startCameraWithoutUri(includeCamera = true, includeGallery = true)
        }


    }


    private fun startCameraWithoutUri(includeCamera: Boolean, includeGallery: Boolean) {
        customCropImage.launch(
            CropImageContractOptions(
                uri = null,
                cropImageOptions = CropImageOptions(
                    imageSourceIncludeCamera = includeCamera,
                    imageSourceIncludeGallery = includeGallery,
                ),
            ),
        )
    }

    private fun startCameraWithUri() {
        cropImage.launch(
            CropImageContractOptions(
                uri = outputUri,
                cropImageOptions = CropImageOptions(),
            ),
        )
    }

    private fun showErrorMessage(message: String) {

        Toast.makeText(activity, "Crop failed: $message", Toast.LENGTH_SHORT).show()
    }

    private fun handleCropImageResult(uri: String) {
        var mUri=Uri.parse(uri)


        binding.takePhoto.setImageURI(mUri)

        //  SampleResultScreen.start(this, null, Uri.parse(uri.replace("file:", "")), null)
    }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorBinding.inflate(layoutInflater, container, false)



        return binding.root
    }


}