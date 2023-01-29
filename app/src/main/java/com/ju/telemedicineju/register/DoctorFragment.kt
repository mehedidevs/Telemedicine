package com.ju.telemedicineju.register

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.ju.telemedicineju.R
import com.ju.telemedicineju.ValidateInputField
import com.ju.telemedicineju.databinding.FragmentDoctorBinding

class DoctorFragment : Fragment() {

    lateinit var binding: FragmentDoctorBinding
    lateinit var progressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorBinding.inflate(inflater)

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


}