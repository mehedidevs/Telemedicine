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
import com.ju.telemedicineju.databinding.FragmentDoctorBinding
import com.ju.telemedicineju.databinding.FragmentLoginBinding
import com.ju.telemedicineju.model.input.Doctor
import com.ju.telemedicineju.model.input.LatLong


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    lateinit var progressDialog: Dialog


    var email: String = ""
    var pass: String = ""





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = Dialog(requireContext())
        progressDialog.setTitle("Please wait...")


        binding.loginBtn.setOnClickListener {

            email = binding.email.text.toString()
            pass = binding.pass.text.toString()


            if (email.equals("")) {
                ValidateInputField(requireContext(), "Email field can't be empty!")
            }else if (pass.equals("")) {
                ValidateInputField(requireContext(), "Password field can't be empty!")
            }else {
                progressDialog.show()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful){
                        progressDialog.dismiss()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }.addOnFailureListener {
                    ValidateInputField(requireContext(), it.localizedMessage.toString())
                }
            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

}