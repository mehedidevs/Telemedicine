package com.ju.telemedicineju.main_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentDoctorsBinding


class DoctorsFragment : Fragment() {

    lateinit var binding : FragmentDoctorsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorsBinding.inflate(inflater)



        return binding.root
    }


}