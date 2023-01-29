package com.ju.telemedicineju.register

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ju.telemedicineju.R
import com.ju.telemedicineju.databinding.FragmentRegisterBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlin.math.ln


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding

    var location : Location? = null
    var lat : Double = 0.0
    var lng : Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)


        takePermission()

        binding.patientBtn.setOnClickListener {
            val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_patientFragment, bundle)
        }
        binding.pdoctorBtn.setOnClickListener {
            //val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_doctorFragment)
        }

        binding.diagnosisBtn.setOnClickListener {
            //val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_diagnosisCenterFragment)
        }
        binding.medicineBtn.setOnClickListener {
            //val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_medicineFragment)
        }
        binding.localPractBtn.setOnClickListener {
            //val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_localPractitiorFragment)
        }

        binding.pharmacyBtn.setOnClickListener {
            //val bundle = bundleOf("lat" to lat)
            findNavController().navigate(R.id.action_registerFragment_to_pharmacyFragment)
        }


        return binding.root
    }

    private fun takePermission() {
        Dexter.withContext(context)
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()){

                        location = getLastLocation()

                        if (location != null){
                            lat = location!!.latitude
                            lng = location!!.longitude

                            Log.d("TAG", "onPermissionsChecked:  $lat $lng", )
                            Toast.makeText(context,"lat: $lat  and  lng: $lng", Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) { /* ... */
                }
            }).check()
    }

    fun getLastLocation(): Location? {
        var mylocation: Location? = null
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager.getProviders(true)
        var bestlocation: Location? = null
        for (provider in providers) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return null
            }
            mylocation = locationManager.getLastKnownLocation(provider!!)
            if (mylocation == null) {
                continue
            }
            if (bestlocation == null || mylocation.accuracy > bestlocation.accuracy) {
                bestlocation = mylocation
            }
        }
        return bestlocation
    }

}