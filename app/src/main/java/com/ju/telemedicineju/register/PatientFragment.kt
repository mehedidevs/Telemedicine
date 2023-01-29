package com.ju.telemedicineju.register

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.ju.telemedicineju.R
import com.ju.telemedicineju.ValidateInputField
import com.ju.telemedicineju.databinding.FragmentPatientBinding


class PatientFragment : Fragment() {

    lateinit var binding: FragmentPatientBinding
    lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientBinding.inflate(inflater)

        var  lat = arguments?.getDouble("lat")
       /* if (lat != null){
            binding.text.text = lat.toString()
        }*/

        Log.i("TAG", "onCreateView: "+lat)

        progressDialog = Dialog(requireContext())
        progressDialog.setTitle("Please wait...")


        binding.takePhoto.setOnClickListener {
            ImagePicker.with(requireActivity())
                .crop()
                .compress(1024)
                .start(101)
        }

        binding.registerBtn.setOnClickListener {
            var name = binding.name.text.toString()
            var email = binding.email.text.toString()
            var phone = binding.phone.text.toString()
            var pass = binding.phone.text.toString()
            var address = binding.address.text.toString()
            var postOffice = binding.postOffice.text.toString()
            var policeStation = binding.policeStation.text.toString()
            var district = binding.district.text.toString()
            if (name.equals("")){
                ValidateInputField(requireContext(),"Name field can't be empty!")
            }else if (email.equals("")){
                ValidateInputField(requireContext(),"Email field can't be empty!")
            }else if (phone.equals("")){
                ValidateInputField(requireContext(),"Phone field can't be empty!")
            }else if (address.equals("")){
                ValidateInputField(requireContext(),"Address field can't be empty!")
            }else if (postOffice.equals("")){
                ValidateInputField(requireContext(),"Post office field can't be empty!")
            }else if (policeStation.equals("")){
                ValidateInputField(requireContext(),"Police station field can't be empty!")
            }else if (district.equals("")){
                ValidateInputField(requireContext(),"District station field can't be empty!")
            }else if (pass.equals("")){
                ValidateInputField(requireContext(),"Password field can't be empty!")
            }else if (pass.length<6){
                ValidateInputField(requireContext(),"Password must be more than 6 character!")
            }else{
                progressDialog.show()
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        progressDialog.dismiss()
                        findNavController().navigate(R.id.action_patientFragment_to_homeFragment)
                    }

                }.addOnFailureListener {
                    progressDialog.dismiss()
                    it.localizedMessage?.let { it1 ->
                        ValidateInputField(requireContext(), it1.toString())
                    }
                }
            }

        }


        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK && data != null){
            var photo = data.data
            binding.previewPhoto.visibility
            Log.i("TAG", "onActivityResult: "+data.data)
            binding.previewPhoto.setImageURI(photo)
        }
    }


}