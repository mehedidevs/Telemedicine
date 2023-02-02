package com.ju.telemedicineju.register

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.ju.telemedicineju.databinding.FragmentPatientBinding
import com.ju.telemedicineju.model.input.Doctor
import com.ju.telemedicineju.model.input.LatLong
import com.ju.telemedicineju.model.input.Patient


class PatientFragment : Fragment() {

    private var _binding: FragmentPatientBinding? = null
    private val binding get() = _binding!!

    private var outputUri: Uri? = null
    lateinit var progressDialog: Dialog

    var name : String = ""
    var email : String = ""
    var phone : String = ""
    var pass : String = ""
    var address : String = ""
    var gender : String = ""
    var date_of_Birth : String = ""
    var post_Office : String = ""
    var police_Station : String = ""
    var district : String = ""
    var photoUrl: String = ""
    var user_id : String = ""


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
            name = binding.name.text.toString()
            email = binding.email.text.toString()
            phone = binding.phone.text.toString()
            pass = binding.pass.text.toString()
            address = binding.address.text.toString()
            gender = binding.gender.text.toString()
            date_of_Birth = binding.dateOfBirth.text.toString()
            police_Station = binding.policeStation.text.toString()
            post_Office = binding.postOffice.text.toString()
            district = binding.district.text.toString()

            if (name.equals("")){
                ValidateInputField(requireContext(),"Name field can't be empty!")
            }else if (email.equals("")){
                ValidateInputField(requireContext(),"Email field can't be empty!")
            }else if (phone.equals("")){
                ValidateInputField(requireContext(),"Phone field can't be empty!")
            }else if (address.equals("")){
                ValidateInputField(requireContext(),"Address field can't be empty!")
            }else if (gender.equals("")){
                ValidateInputField(requireContext(),"Gender field can't be empty!")
            }else if (date_of_Birth.equals("")){
                ValidateInputField(requireContext(),"Date of Birth field can't be empty!")
            }else if (district.equals("")){
                ValidateInputField(requireContext(),"District field can't be empty!")
            }else if (district.equals("")){
                ValidateInputField(requireContext(),"District field can't be empty!")
            }else if (post_Office.equals("")){
                ValidateInputField(requireContext(),"Post office field can't be empty!")
            }else if (police_Station.equals("")){
                ValidateInputField(requireContext(),"Police station field can't be empty!")
            }else if (pass.length<6){
                ValidateInputField(requireContext(),"Password must be more than 6 character!")
            }else{
                progressDialog.show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, pass!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            outputUri?.let { it1 -> uploadPhotoToStorage(it1) }
                            //progressDialog.dismiss()
                            //findNavController().navigate(R.id.action_patientFragment_to_homeFragment)
                        }

                    }.addOnFailureListener {
                        progressDialog.dismiss()
                        it.localizedMessage?.let { it1 ->
                            ValidateInputField(requireContext(), it1.toString())
                        }
                    }
            }

        }


    }

    private fun uploadPhotoToStorage(outputUri: Uri) {
        val storage = Firebase.storage
        var storageRef = storage.reference
        var current_user = FirebaseAuth.getInstance().currentUser
        if (current_user != null) {
            user_id = current_user.uid
        }
        var imagesRef: StorageReference? =
            storageRef.child("photos").child(user_id + System.currentTimeMillis())

        if (imagesRef != null) {
            outputUri.let { imagesRef.putFile(it) }.addOnCompleteListener {
                if (it.isSuccessful) {
                    imagesRef.downloadUrl.addOnCompleteListener { task2 ->

                        val photoUri =task2.result
                        var photoUrl = photoUri.toString()

                        uploadToDatabase(photoUrl)
                    }


                }

            }.addOnFailureListener {
                progressDialog.dismiss()
                it.localizedMessage?.let { it1 ->
                    ValidateInputField(
                        requireContext(),
                        it1.toString()
                    )
                }
            }
        }
    }

    private fun uploadToDatabase(photoUri: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Users")
        val databaseRef = myRef.child("Patients")
        var lat_lng =  LatLong("","")
        if (user_id != null){
            var patient = Patient(
                user_id,
                name,
                gender,
                date_of_Birth,
                phone,
                email,
                address,
                post_Office,
                police_Station,
                district,
                photoUri,
                lat_lng
            )

            databaseRef.child(user_id)
                .setValue(patient).addOnCompleteListener {
                    if (it.isSuccessful){
                        progressDialog.dismiss()
                        findNavController().navigate(R.id.action_patientFragment_to_homeFragment)
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
        _binding = FragmentPatientBinding.inflate(layoutInflater, container, false)



        return binding.root
    }





}