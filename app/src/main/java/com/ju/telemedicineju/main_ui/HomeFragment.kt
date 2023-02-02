package com.ju.telemedicineju.main_ui

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ju.telemedicineju.R
import com.ju.telemedicineju.adapter.SpecialistDoctorAdapter
import com.ju.telemedicineju.databinding.FragmentHomeBinding

import com.ju.telemedicineju.model.input.Doctor

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    lateinit var progressDialog: Dialog

    val list = ArrayList<Doctor>()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val specialDocRef = FirebaseDatabase.getInstance().getReference("Users").child("Doctors")


        binding.doctorBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_doctorsFragment)
        }

        specialDocRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                val data = snapshot.children
                data.forEach{snp ->
                    val doctor:Doctor=snp.getValue(Doctor::class.java)!!

                    list.add(doctor)
                }

                val adapter = SpecialistDoctorAdapter(list,requireContext())
                binding.speceialistDoctor.adapter = adapter
                binding.ourDoctorRcv.adapter = adapter

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
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}