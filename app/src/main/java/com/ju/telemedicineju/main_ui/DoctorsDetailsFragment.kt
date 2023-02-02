package com.ju.telemedicineju.main_ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ju.telemedicineju.R
import com.ju.telemedicineju.adapter.DoctorsAdapter
import com.ju.telemedicineju.databinding.FragmentDoctorsBinding
import com.ju.telemedicineju.databinding.FragmentDoctorsDetailsBinding
import com.ju.telemedicineju.model.input.Doctor
import com.squareup.picasso.Picasso

class DoctorsDetailsFragment : Fragment() {
    private lateinit var mMap: GoogleMap

    private var _binding: FragmentDoctorsDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var progressDialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id")
        Log.i("TAG", "onCreateView: "+ id)

        val docDetails = FirebaseDatabase.getInstance().getReference("Users").child("Doctors").child(id!!)

        docDetails.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val doctor: Doctor =snapshot.getValue(Doctor::class.java)!!
                binding.nameTv.text = doctor.Full_Name
                binding.spelizationTv.text = doctor.Specialization
                binding.adressTv.text = doctor.Address
                binding.feeTv.text = "Fee: "+doctor.Fee + " tk"
                Log.i("TAG", "onDataChange: "+doctor.Profile_Photo)
                Picasso.get().load(doctor.Profile_Photo).into(binding.profileImage);
              // Glide.with(requireActivity()).load(doctor.Profile_Photo).placeholder(R.drawable.ic_doctor).into(binding.profileImage)


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoctorsDetailsBinding.inflate(inflater)



        return binding.root
    }

}