package com.ju.telemedicineju

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.internal.ContextUtils.getActivity
import com.ju.telemedicineju.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setContentView(binding.root)


        takePermission()

//        val frg = binding.fragmentContainerView.getFragment<Fragment>()
//
//        val f:Fragment= get.findFragmentById(R.id.fragmentContainerView)!!
//
//        if (frg.is) {
//            binding.bottomNav.visibility = View.VISIBLE
//
//        } else {
//            binding.bottomNav.visibility = View.GONE
//        }




    }


    private fun takePermission() {
        Dexter.withContext(this)
            .withPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()){

//                        location = getLastLocation()
//
//                        if (location != null){
//                            lat = location!!.latitude
//                            lng = location!!.longitude
//
//                            Log.d("TAG", "onPermissionsChecked:  $lat $lng", )
//                            Toast.makeText(context,"lat: $lat  and  lng: $lng", Toast.LENGTH_LONG).show()
//                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) { /* ... */
                }
            }).check()
    }
}