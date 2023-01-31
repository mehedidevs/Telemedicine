package com.ju.telemedicineju.register

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.ju.telemedicineju.R
import com.ju.telemedicineju.ValidateInputField
import com.ju.telemedicineju.databinding.FragmentDiagnosisCenterBinding
import com.ju.telemedicineju.databinding.FragmentMedicineBinding
import com.ju.telemedicineju.model.input.DiagnosisCenter
import com.ju.telemedicineju.model.input.LatLong
import com.ju.telemedicineju.model.input.Medicine

class MedicineFragment : Fragment() {


    private var _binding: FragmentMedicineBinding? = null
    private val binding get() = _binding!!

    private var outputUri: Uri? = null
    lateinit var progressDialog: Dialog

    var mName : String = ""
    var gName : String = ""
    var bName : String = ""
    var price : String = ""

    var photoUrl: String = ""
    var medicine_id : String? = ""

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

        progressDialog = Dialog(requireContext())
        progressDialog.setTitle("Please wait...")

        binding.takePhoto.setOnClickListener {
            startCameraWithoutUri(includeCamera = true, includeGallery = true)
        }

        binding.registerBtn.setOnClickListener {
            mName = binding.mName.text.toString()
            gName = binding.gName.text.toString()
            bName = binding.bName.text.toString()
            price = binding.price.text.toString()

            if (mName.equals("")){
                ValidateInputField(requireContext(),"Medicine Name field can't be empty!")
            }else if (gName.equals("")){
                ValidateInputField(requireContext(),"Group name field can't be empty!")
            }else if (bName.equals("")){
                ValidateInputField(requireContext(),"Brand name field can't be empty!")
            }else if (price.equals("")){
                ValidateInputField(requireContext(),"Price field can't be empty!")
            }else{
                progressDialog.show()
                uploadPhotoToStorage()
                /*FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, pass!!).addOnCompleteListener{
                    if (it.isSuccessful){
                        uploadPhotoToStorage()
                        //progressDialog.dismiss()
                        //findNavController().navigate(R.id.action_patientFragment_to_homeFragment)
                    }

                }.addOnFailureListener {
                    progressDialog.dismiss()
                    it.localizedMessage?.let { it1 ->
                        ValidateInputField(requireContext(), it1.toString())
                    }
                }*/
            }

        }


    }

    private fun uploadPhotoToStorage() {
        val storage = Firebase.storage
        var storageRef = storage.reference
        var imagesRef: StorageReference? = storageRef.child("photos").child(""+System.currentTimeMillis())

        if (imagesRef != null) {
            outputUri?.let { imagesRef.putFile(it) }?.addOnCompleteListener {
                if (it.isSuccessful){
                    val photoUri = it.result
                    photoUrl = photoUri.toString()

                    uploadToDatabase()
                }

            }?.addOnFailureListener {
                progressDialog.dismiss()
                it.localizedMessage?.let { it1 ->
                    ValidateInputField(requireContext(),
                        it1.toString())
                }
            }
        }
    }

    private fun uploadToDatabase() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")
        val databaseRef = myRef.child("Medicine")
        medicine_id = databaseRef.push().key
        var lat_lng =  LatLong("","")
        if (medicine_id != null){
            var medicine = Medicine(
                medicine_id!!,
                mName,
                gName,
                bName,
                price,
                photoUrl,
                lat_lng
            )

            databaseRef.child(medicine_id!!)
                .setValue(medicine).addOnCompleteListener {
                    if (it.isSuccessful){
                        progressDialog.dismiss()
                        findNavController().navigate(R.id.action_medicineFragment_to_homeFragment)
                    }
                }.addOnFailureListener {
                    progressDialog.dismiss()
                    ValidateInputField(requireContext(),it.localizedMessage.toString())
                }
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



    private fun showErrorMessage(message: String) {

        Toast.makeText(activity, "Crop failed: $message", Toast.LENGTH_SHORT).show()
    }

    private fun handleCropImageResult(uri: String) {
        outputUri= Uri.parse(uri)
        binding.takePhoto.setImageURI(outputUri)
        Log.i("TAG", "handleCropImageResult: "+outputUri)

        //  SampleResultScreen.start(this, null, Uri.parse(uri.replace("file:", "")), null)
    }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMedicineBinding.inflate(layoutInflater, container, false)



        return binding.root
    }


}