package com.ju.telemedicineju.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentPatientBinding


class PatientFragment : Fragment() {

    lateinit var binding: FragmentPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPatientBinding.inflate(inflater)

        var  lat = arguments?.getString("lat")
        if (lat != null){

        }


        return binding.root
    }


}