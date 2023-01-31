package com.ju.telemedicineju.main_ui

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.ju.telemedicineju.adapter.SpecialistDoctorAdapter
import com.ju.telemedicineju.databinding.FragmentHomeBinding

import com.ju.telemedicineju.model.input.Doctor

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var outputUri: Uri? = null
    lateinit var progressDialog: Dialog

    val list = ArrayList<Doctor>()





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val specialDocRef = FirebaseDatabase.getInstance().getReference("Users").child("Doctors")

        specialDocRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.children
                data.forEach{snp ->
                    val doctor:Doctor=snp.getValue(Doctor::class.java)!!

                    list.add(doctor)
                }

                val adapter = SpecialistDoctorAdapter(list,requireContext())
                binding.speceialistDoctor.adapter = adapter

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